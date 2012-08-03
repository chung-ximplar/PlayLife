package com.playlife.persistenceLayer.DAO;

import java.util.Date;
import java.util.List;

import com.playlife.persistenceLayer.DAO.genericDAO.IGenericDAO;
import com.playlife.persistenceLayer.DAO.genericDAO.Param;
import com.playlife.persistenceLayer.domainObject.UserLog;
import com.playlife.persistenceLayer.domainObject.UserLog_Type;

public interface UserLogDAO extends IGenericDAO<UserLog, Long>{
	List<UserLog> hql_find_ByIpAndTypeAndDate(@Param(name="ip") String ip, @Param(name="type") UserLog_Type type, 
			@Param(name="createdDate") Date createdDate, @Param(name="interval")int interval);
}