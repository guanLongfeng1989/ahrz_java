package com.cn.riwise.general.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.riwise.general.dao.LocationDao;
import com.cn.riwise.general.pojo.LocationObj;
import com.cn.riwise.general.service.LocationService;

@Service("LocationServiceImpl")
public class LocationServiceImpl implements LocationService {
    @Resource
    LocationDao LocationDao;
	public List<LocationObj> getLocationObj(LocationObj location) {
		// TODO Auto-generated method stub
		return LocationDao.getLocationObj(location);
	}

}
