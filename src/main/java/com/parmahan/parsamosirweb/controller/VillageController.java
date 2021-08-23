package com.parmahan.parsamosirweb.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.parmahan.parsamosirweb.constant.PageConstant;
import com.parmahan.parsamosirweb.constant.ResponseConstant;
import com.parmahan.parsamosirweb.handler.PaginationHandler;
import com.parmahan.parsamosirweb.handler.ResponseHandler;
import com.parmahan.parsamosirweb.model.Place;
import com.parmahan.parsamosirweb.model.User;
import com.parmahan.parsamosirweb.model.admin.District;
import com.parmahan.parsamosirweb.model.admin.Village;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class VillageController {
	Logger logger = LoggerFactory.getLogger(VillageController.class);
	@Autowired
	private RestTemplate restTemplate;

	@Value("${backend-api.url}")
	private String BACKEND_API_URL;

	@GetMapping(value = "/villages")
	@SuppressWarnings("unchecked")
	public String getAllVillages(Model model) {
		User user = new User();
		user.setUserName(BACKEND_API_URL);

		ResponseEntity<LinkedHashMap<String, Object>> responseEntity =
				restTemplate.exchange(BACKEND_API_URL + "/admin/village/allitems", HttpMethod.GET, null,
						new ParameterizedTypeReference<LinkedHashMap<String, Object>>() {});

		List<Village> villageList =
				(List<Village>) (Object) ResponseHandler.handleResponseEntityList(responseEntity);

		model.addAttribute("villageList", villageList);

		PaginationHandler.handlePaginationParam(model, responseEntity);
		return PageConstant.ADMIN_VILLAGES;
	}

	@GetMapping(
			value = "/villages/pageNo={pageNo}&size={pageSize}&sortBy={sortBy}&sortType={sortType}")
	@SuppressWarnings("unchecked")
	public String getVillagesByPage(Model model, @PathVariable int pageNo,
			@PathVariable int pageSize, @PathVariable String sortBy, @PathVariable String sortType)
			throws JsonMappingException, JsonProcessingException {
		User user = new User();
		user.setUserName(BACKEND_API_URL);

		ResponseEntity<LinkedHashMap<String, Object>> responseEntity = restTemplate.exchange(
				BACKEND_API_URL + "/admin/village/allitems/pageNo=" + pageNo + "&pageSize=" + pageSize
						+ "&SortBy=" + sortBy + "&SortType=" + sortType,
				HttpMethod.GET, null,
				new ParameterizedTypeReference<LinkedHashMap<String, Object>>() {});

		List<Village> villageList =
				(List<Village>) (Object) ResponseHandler.handleResponseEntityList(responseEntity);
		model.addAttribute("villageList", villageList);
		PaginationHandler.handlePaginationParam(model, responseEntity);
		model.addAttribute(ResponseConstant.CURRENT_PAGE, pageNo);
		return PageConstant.ADMIN_VILLAGES;
	}

	@GetMapping(value = "/village/update/id/{id}")
	public String showUpdateForm(Model model, @PathVariable String id)
			throws JsonMappingException, JsonProcessingException {

		ResponseEntity<LinkedHashMap<String, Object>> responseEntity =
				restTemplate.exchange(BACKEND_API_URL + "/admin/village/id/" + id, HttpMethod.GET, null,
						new ParameterizedTypeReference<LinkedHashMap<String, Object>>() {});

		Village village = (Village) ResponseHandler.handleResponseEntity(responseEntity);
		model.addAttribute("village", village);
		return PageConstant.ADMIN_VILLAGE_UPDATE;
	}

	@GetMapping(value = "/villages/districtCode={code}")
	@SuppressWarnings("unchecked")
	public String getVillagesByDistrict(Model model, @PathVariable String code) {

		ResponseEntity<LinkedHashMap<String, Object>> responseEntity = restTemplate.exchange(
				BACKEND_API_URL + "/admin/village/district/" + code, HttpMethod.GET, null,
				new ParameterizedTypeReference<LinkedHashMap<String, Object>>() {});
		List<Village> villageList =
				(List<Village>) (Object) ResponseHandler.handleResponseEntityList(responseEntity);

		model.addAttribute("villageList", villageList);
		return PageConstant.ADMIN_VILLAGE_BY_DISTRICT_CODE;
	}

	@GetMapping(value = "/villagesByDistrict")
	@SuppressWarnings("unchecked")
	public String getDistricts(Model model) {

		// List<Village> villageList = new ArrayList<>();
		List<Village> villageByDistrictList = new ArrayList<>();

		LinkedHashMap<District, List<Village>> villageByDistrictMap = new LinkedHashMap<>();
		ResponseEntity<LinkedHashMap<String, Object>> responseEntityDistrict =
				restTemplate.exchange(BACKEND_API_URL + "/admin/district/all", HttpMethod.GET, null,
						new ParameterizedTypeReference<LinkedHashMap<String, Object>>() {});

		List<District> districtList = (List<District>) (Object) ResponseHandler
				.handleResponseEntityList(responseEntityDistrict);

		ObjectMapper mapper = new ObjectMapper();
		mapper.findAndRegisterModules();

		List<District> actualTList =
				mapper.convertValue(districtList, new TypeReference<List<District>>() {});
		logger.info("actualTList" + actualTList);
		for (District district : actualTList) {
			ResponseEntity<LinkedHashMap<String, Object>> responseEntityVillage = restTemplate
					.exchange(BACKEND_API_URL + "/admin/village/districts/" + district.getCode(),
							HttpMethod.GET, null,
							new ParameterizedTypeReference<LinkedHashMap<String, Object>>() {});
			logger.info("districtList: " + district.getName() + "\n");
			villageByDistrictList = (List<Village>) (Object) ResponseHandler
					.handleResponseEntityList(responseEntityVillage);

			villageByDistrictMap.put(district, villageByDistrictList);
		}
		model.addAttribute("villageList", villageByDistrictMap);

		return PageConstant.ADMIN_VILLAGE_BY_DISTRICT;
	}

	@GetMapping(value = "/village/name={villageName}")
	@SuppressWarnings("unchecked")
	public String getVillageDetails(Model model, @PathVariable String villageName) {


		ResponseEntity<LinkedHashMap<String, Object>> responseEntityPlaces = restTemplate.exchange(
				BACKEND_API_URL + "/place/villageName=" + villageName, HttpMethod.GET, null,
				new ParameterizedTypeReference<LinkedHashMap<String, Object>>() {});

		List<Place> placeList =
				(List<Place>) (Object) ResponseHandler.handleResponseEntityList(responseEntityPlaces);
		logger.info(placeList.toString());
		model.addAttribute("placeList", placeList);
		return PageConstant.ADMIN_VILLAGE_DETAIL;
	}

	@GetMapping(value = {"/village/add", "/village/add/name={name}&district={district}"})
	@SuppressWarnings("unchecked")
	public String addNewVillage(Model model, @PathVariable(name = "name") Optional<String> name,
			@PathVariable(name = "district") Optional<String> districtId) {

		District district = new District();
		String strName = "";
		// Get all districts
		ResponseEntity<LinkedHashMap<String, Object>> responseEntity =
				restTemplate.exchange(BACKEND_API_URL + "/admin/district/all", HttpMethod.GET, null,
						new ParameterizedTypeReference<LinkedHashMap<String, Object>>() {});

		if (districtId.isPresent()) {
			ResponseEntity<LinkedHashMap<String, Object>> responseEntityDistrict = restTemplate
					.exchange(BACKEND_API_URL + "/admin/district/id/" + districtId.get(), HttpMethod.GET, null,
							new ParameterizedTypeReference<LinkedHashMap<String, Object>>() {});

			ObjectMapper mapper = new ObjectMapper();
			mapper.findAndRegisterModules();
			district = mapper.convertValue(responseEntityDistrict.getBody().get(ResponseConstant.DATA),
					District.class);
		}

		if(name.isPresent()){
			strName = name.get();
		}

		List<District> districtList =
				(List<District>) (Object) ResponseHandler.handleResponseEntityList(responseEntity);
		logger.info("districtList: " + districtList.toString());
		model.addAttribute("districtList", districtList);
		model.addAttribute("attrDistrict", district);
		model.addAttribute("name", strName);
		model.addAttribute("villageType", "D");
		return PageConstant.ADMIN_VILLAGE_ADD;
	}

	@PostMapping(value = "/village/confirm")
	public String confirmNewVillage(Model model, @ModelAttribute("name") String name,
			@ModelAttribute("villageType") String villageType,
			@ModelAttribute("district") String districtId) {
		logger.info("Village: " + name);
		logger.info("District: " + districtId.toString());

		ResponseEntity<LinkedHashMap<String, Object>> responseEntity = restTemplate.exchange(
				BACKEND_API_URL + "/admin/district/id/" + districtId, HttpMethod.GET, null,
				new ParameterizedTypeReference<LinkedHashMap<String, Object>>() {});

		ObjectMapper mapper = new ObjectMapper();
		mapper.findAndRegisterModules();
		District district = mapper.convertValue(responseEntity.getBody().get(ResponseConstant.DATA),
				District.class);

		Village village = new Village();
		village.setName(name);
		village.setDistrict(district);

		model.addAttribute("name", name);
		model.addAttribute("district", district);
		model.addAttribute("villageType", villageType);
		return PageConstant.ADMIN_VILLAGE_CONFIRM;
	}

	@PostMapping(value = "/village/submit")
	public String submitNewVillage(Model model, @ModelAttribute("name") String name,
			@ModelAttribute("villageType") String villageType,
			@ModelAttribute("district") String districtId) {
		logger.info("Village: " + name);
		logger.info("District: " + districtId.toString());
		ResponseEntity<LinkedHashMap<String, Object>> responseEntityDistrict = restTemplate.exchange(
				BACKEND_API_URL + "/admin/district/id/" + districtId, HttpMethod.GET, null,
				new ParameterizedTypeReference<LinkedHashMap<String, Object>>() {});

		ObjectMapper mapper = new ObjectMapper();
		mapper.findAndRegisterModules();
		District district = mapper.convertValue(
				responseEntityDistrict.getBody().get(ResponseConstant.DATA), District.class);
		Village village = new Village();
		village.setName(name);
		village.setType(villageType);
		village.setDistrict(district);

		logger.info(village.toString());

		ResponseEntity<String> result = restTemplate
				.postForEntity(BACKEND_API_URL + "/admin/village/save", village, String.class);


		model.addAttribute("name", name);
		model.addAttribute("district", district);

		return "redirect:/villages";
	}
}
