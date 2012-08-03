package com.playlife.persistenceLayer.DAO;

import com.playlife.persistenceLayer.DAO.genericDAO.IGenericDAO;
import com.playlife.persistenceLayer.DAO.genericDAO.Param;
import com.playlife.persistenceLayer.domainObject.BookSet;

public interface BookSetDAO extends IGenericDAO<BookSet, Long> {
	void sql_update_DeletedByBookSetId(@Param(name="bookSetId") Long bookSetId);
}
