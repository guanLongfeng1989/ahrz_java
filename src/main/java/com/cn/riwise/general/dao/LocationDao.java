package com.cn.riwise.general.dao;

import java.util.List;

import com.cn.riwise.general.pojo.LocationObj;

public interface LocationDao {
    /**
     * 获取地址list
     * @param location
     * @return
     */
	public List<LocationObj> getLocationObj(LocationObj location) ;

}
