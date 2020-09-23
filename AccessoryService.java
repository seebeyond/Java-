package model.accessory;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import common.accessory.AccessoryCommon;

import smart.css.database.DBEntity;
import formbean.accessory.AttachmentBean;
import formbean.accessory.AttachmentTypeBean;

public class AccessoryService {
	private final static Logger log = Logger.getLogger(AccessoryService.class);
	/******************************************************
	 * @docRoot   根据owner, ownerID, ownerLevel查寻附件信息．
	 * @author 	  freewind
	 * @param	  owner, ownerID, ownerLevel
	 * @return	  附件数组
	 * @exception SQLException(数据库异常)
	 *******************************************************/
    public ArrayList findAccessoryList(String owner, String ownerID, String ownerLevel, String isMeet) 
    throws SQLException {
    	if (owner == null || "".equals(owner)) {
    		throw new SQLException("owner 不能为空");
    	}
    	if (ownerID == null || "".equals(ownerID)) {
    		ownerID = "null";
    	}
    	if (ownerLevel == null || "".equals(ownerLevel)) {
    		ownerLevel = "null";
    	}
    	DBEntity dbUtil = new DBEntity();
    	ResultSet rs = null;
    	ArrayList dataList = new ArrayList();
    	try {
			rs = dbUtil.executeQuery(makeFindAccType(owner, ownerID, ownerLevel, isMeet));
            while (rs.next()) {
           	 AttachmentTypeBean atBean = new AttachmentTypeBean();
           	 atBean.setAutoID(rs.getString(AccessoryCommon.catAutoID));
           	 atBean.setOwner(rs.getString(AccessoryCommon.catOwner));
           	 atBean.setOwnerID(rs.getString(AccessoryCommon.catOwnerID));
           	 atBean.setOwnerLevel(rs.getString(AccessoryCommon.catOwnerLevel));
           	 atBean.setCatalogValue(rs.getString(AccessoryCommon.catCatalogValue));
           	 atBean.setFlag(rs.getString(AccessoryCommon.catFlag));
           	 dataList.add(atBean);
            }
		} catch (SQLException e) {
			log.error(e.getMessage());
			throw new SQLException(e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new SQLException(e.getMessage());
		} finally {
			try {
				rs.close();
				dbUtil.close();
			} catch (SQLException e) {
				rs = null;
				dbUtil = null;
			} catch (Exception e) {
				rs = null;
				dbUtil = null;
			}
		}
    	return dataList;
    }
    public ArrayList findAccessoryList2(String owner, String ownerID, String ownerLevel, String isMeet) 
    throws SQLException {
    	if (owner == null || "".equals(owner)) {
    		throw new SQLException("owner 不能为空");
    	}
    	if (ownerID == null || "".equals(ownerID)) {
    		ownerID = "null";
    	}
    	if (ownerLevel == null || "".equals(ownerLevel)) {
    		ownerLevel = "null";
    	}
    	DBEntity dbUtil = new DBEntity();
    	ResultSet rs = null;
    	ArrayList dataList = new ArrayList();
    	try {
			rs = dbUtil.executeQuery(makeFindAccType2(owner, ownerID, ownerLevel, isMeet));
            while (rs.next()) {
           	 AttachmentTypeBean atBean = new AttachmentTypeBean();
           	 atBean.setAutoID(rs.getString(AccessoryCommon.catAutoID));
           	 atBean.setOwner(rs.getString(AccessoryCommon.catOwner));
           	 atBean.setOwnerID(rs.getString(AccessoryCommon.catOwnerID));
           	 atBean.setOwnerLevel(rs.getString(AccessoryCommon.catOwnerLevel));
           	 atBean.setCatalogValue(rs.getString(AccessoryCommon.catCatalogValue));
           	 atBean.setFlag(rs.getString(AccessoryCommon.catFlag));
           	 atBean.setFilecount(rs.getString("file_count"));
           	 dataList.add(atBean);
            }
		} catch (SQLException e) {
			log.error(e.getMessage());
			throw new SQLException(e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new SQLException(e.getMessage());
		} finally {
			try {
				rs.close();
				dbUtil.close();
			} catch (SQLException e) {
				rs = null;
				dbUtil = null;
			} catch (Exception e) {
				rs = null;
				dbUtil = null;
			}
		}
    	return dataList;
    }
    public ArrayList findAccessoryList2(String ownerID, String ownerLevel, String isMeet) 
    throws SQLException {
    	if (ownerID == null || "".equals(ownerID)) {
    		ownerID = "null";
    	}
    	if (ownerLevel == null || "".equals(ownerLevel)) {
    		ownerLevel = "null";
    	}
    	DBEntity dbUtil = new DBEntity();
    	ResultSet rs = null;
    	ArrayList dataList = new ArrayList();
    	try {
			rs = dbUtil.executeQuery(makeFindAccType2(ownerID, ownerLevel, isMeet));
            while (rs.next()) {
           	 AttachmentTypeBean atBean = new AttachmentTypeBean();
           	 atBean.setAutoID(rs.getString(AccessoryCommon.catAutoID));
           	 atBean.setOwner(rs.getString(AccessoryCommon.catOwner));
           	 atBean.setOwnerID(rs.getString(AccessoryCommon.catOwnerID));
           	 atBean.setOwnerLevel(rs.getString(AccessoryCommon.catOwnerLevel));
           	 atBean.setCatalogValue(rs.getString(AccessoryCommon.catCatalogValue));
           	 atBean.setFlag(rs.getString(AccessoryCommon.catFlag));
           	 atBean.setFilecount(rs.getString("file_count"));
           	 dataList.add(atBean);
            }
		} catch (SQLException e) {
			log.error(e.getMessage());
			throw new SQLException(e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new SQLException(e.getMessage());
		} finally {
			try {
				rs.close();
				dbUtil.close();
			} catch (SQLException e) {
				rs = null;
				dbUtil = null;
			} catch (Exception e) {
				rs = null;
				dbUtil = null;
			}
		}
    	return dataList;
    }
    /******************************************************
	 * @docRoot   根据owner, ownerID, ownerLevel查寻附件在特定
	 * 	　　　　　　阶段的信息
	 * @author 	  freewind
	 * @param	  owner, ownerID, ownerLevel
	 * @return	  附件数组
	 * @exception SQLException(数据库异常)
	 *******************************************************/
    public ArrayList findDocumentList(String owner, String ownerID, String ownerLevel) 
    throws SQLException {
    	if (owner == null || "".equals(owner)) {
    		throw new SQLException("owner 不能为空");
    	}
    	if (ownerID == null || "".equals(ownerID)) {
    		ownerID = "null";
    	}
    	if (ownerLevel == null || "".equals(ownerLevel)) {
    		ownerLevel = "null";
    	}
    	DBEntity dbUtil = new DBEntity();
    	ResultSet rs = null;
    	ArrayList dataList = new ArrayList();
    	String sqlDoc = makeFindDocument(owner, ownerID, ownerLevel);
//    	System.out.println("sqlDoc========="+sqlDoc);
    	try {
			rs = dbUtil.executeQuery(sqlDoc);
            while (rs.next()) {
				AttachmentBean aBean = new AttachmentBean();
				aBean.setAutoID(rs.getString(AccessoryCommon.caAutoID));
				aBean.setOwner(rs.getString(AccessoryCommon.caOwner));
				aBean.setOwnerID(rs.getString(AccessoryCommon.caOwnerID));
				aBean.setOwnerLevel(rs.getString(AccessoryCommon.caOwnerLevel));
				aBean.setTitle(rs.getString(AccessoryCommon.caTitle));
//				aBean.setFilepath(rs.getString(AccessoryCommon.caFilePath));
				aBean.setUserID(rs.getString(AccessoryCommon.caUserID));
				aBean.setInputDate(rs.getString("CINPUTDATE"));
				aBean.setCatalogID(rs.getString(AccessoryCommon.caCatalogID));
				aBean.setDocSize(rs.getString(AccessoryCommon.caDocSize));
//				aBean.setOrderid(rs.getString("orderid"));
				dataList.add(aBean);
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
			throw new SQLException(e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new SQLException(e.getMessage());
		} finally {
			try {
				rs.close();
				dbUtil.close();
			} catch (SQLException e) {
				rs = null;
				dbUtil = null;
			} catch (Exception e) {
				rs = null;
				dbUtil = null;
			}
		}
    	return dataList;
    }
    public ArrayList findDocumentList2(String owner, String ownerID, String ownerLevel) 
    throws SQLException {
    	if (owner == null || "".equals(owner)) {
    		throw new SQLException("owner 不能为空");
    	}
    	if (ownerID == null || "".equals(ownerID)) {
    		ownerID = "null";
    	}
    	if (ownerLevel == null || "".equals(ownerLevel)) {
    		ownerLevel = "null";
    	}
    	DBEntity dbUtil = new DBEntity();
    	ResultSet rs = null;
    	ArrayList dataList = new ArrayList();
    	String sqlDoc = makeFindDocument2(owner, ownerID, ownerLevel);
//    	System.out.println("sqlDoc========="+sqlDoc);
    	try {
			rs = dbUtil.executeQuery(sqlDoc);
            while (rs.next()) {
				AttachmentBean aBean = new AttachmentBean();
				aBean.setAutoID(rs.getString(AccessoryCommon.caAutoID));
				aBean.setOwner(rs.getString(AccessoryCommon.caOwner));
				aBean.setOwnerID(rs.getString(AccessoryCommon.caOwnerID));
				aBean.setOwnerLevel(rs.getString(AccessoryCommon.caOwnerLevel));
				aBean.setTitle(rs.getString(AccessoryCommon.caTitle));
//				aBean.setFilepath(rs.getString(AccessoryCommon.caFilePath));
				aBean.setUserID(rs.getString(AccessoryCommon.caUserID));
				aBean.setInputDate(rs.getString("CINPUTDATE"));
				aBean.setCatalogID(rs.getString(AccessoryCommon.caCatalogID));
				aBean.setDocSize(rs.getString(AccessoryCommon.caDocSize));
//				aBean.setOrderid(rs.getString("orderid"));
				dataList.add(aBean);
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
			throw new SQLException(e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new SQLException(e.getMessage());
		} finally {
			try {
				rs.close();
				dbUtil.close();
			} catch (SQLException e) {
				rs = null;
				dbUtil = null;
			} catch (Exception e) {
				rs = null;
				dbUtil = null;
			}
		}
    	return dataList;
    }
    /******************************************************
	 * @docRoot   根据owner, ownerID, ownerLevel查寻附件在特定
	 * 	　　　　　　阶段的信息
	 * @author 	  freewind
	 * @param	  owner, ownerID, ownerLevel
	 * @return	  附件数组
	 * @exception SQLException(数据库异常)
	 *******************************************************/
    public ArrayList findDocumentList3(String ownerID, String ownerLevel) 
    throws SQLException {
//    	if (owner == null || "".equals(owner)) {
//    		throw new SQLException("owner 不能为空");
//    	}
    	if (ownerID == null || "".equals(ownerID)) {
    		ownerID = "null";
    	}
    	if (ownerLevel == null || "".equals(ownerLevel)) {
    		ownerLevel = "null";
    	}
    	DBEntity dbUtil = new DBEntity();
    	ResultSet rs = null;
    	ArrayList dataList = new ArrayList();
//    	String sqlDoc = makeFindDocument(owner, ownerID, ownerLevel);
    	String sqlDoc = makeFindDocument3(ownerID, ownerLevel);
//    	System.out.println("sqlDoc========="+sqlDoc);
    	try {
			rs = dbUtil.executeQuery(sqlDoc);
            while (rs.next()) {
				AttachmentBean aBean = new AttachmentBean();
				aBean.setAutoID(rs.getString(AccessoryCommon.caAutoID));
				aBean.setOwner(rs.getString(AccessoryCommon.caOwner));
				aBean.setOwnerID(rs.getString(AccessoryCommon.caOwnerID));
				aBean.setOwnerLevel(rs.getString(AccessoryCommon.caOwnerLevel));
				aBean.setTitle(rs.getString(AccessoryCommon.caTitle));
//				aBean.setFilepath(rs.getString(AccessoryCommon.caFilePath));
				aBean.setUserID(rs.getString(AccessoryCommon.caUserID));
				aBean.setInputDate(rs.getString("CINPUTDATE"));
				aBean.setCatalogID(rs.getString(AccessoryCommon.caCatalogID));
				aBean.setDocSize(rs.getString(AccessoryCommon.caDocSize));
//				aBean.setOrderid(rs.getString("orderid"));
				dataList.add(aBean);
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
			throw new SQLException(e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new SQLException(e.getMessage());
		} finally {
			try {
				rs.close();
				dbUtil.close();
			} catch (SQLException e) {
				rs = null;
				dbUtil = null;
			} catch (Exception e) {
				rs = null;
				dbUtil = null;
			}
		}
    	return dataList;
    }
    /******************************************************
	 * @docRoot   删除附件（一个）
	 * @author 	  freewind
	 * @param	  autoID, dirPath
	 * @return	  -1 删除失败 1 删除成功
	 * @exception SQLException(数据库异常)
	 *******************************************************/
    public int delDoc(String autoID, String dirPath) throws SQLException {
    	int delNum = 0;
    	DBEntity dbUtil = new DBEntity();
    	ResultSet rs = null;
    	try {
    		rs = dbUtil.executeQuery(makeGetFileName(autoID));
    		rs.first();
    		String fileName = rs.getString(AccessoryCommon.caFilePath);
    		String wholeName = dirPath + fileName;
    		File fileUtil = new File(wholeName);
    		fileUtil.delete();
			boolean delFlag = DBEntity.execute(makeDelAcc(autoID));
			if (delFlag == true) {
				delNum = 1;
			}
		} catch (SQLException e) {
			delNum = -1;
			log.error(e.getMessage());
			throw new SQLException(e.getMessage());
		} catch (Exception e) {
			delNum = -1;
			log.error(e.getMessage());
			throw new SQLException(e.getMessage());
		} finally {

				rs = null;
				dbUtil.close();
		
		}
    	return delNum;
    }
    /******************************************************
	 * @docRoot   添加附件附件
	 * @author 	  freewind
	 * @param	  accBo 附件详细信息
	 * @return	  -1 添加失败 1 添加成功
	 * @exception SQLException(数据库异常)
	 *******************************************************/
    public int addDoc(AccessoryBo accBo) throws SQLException {
    	int addNum = 0;
    	DBEntity dbUtil = new DBEntity();
    	try {
			boolean addFlag = DBEntity.execute(makeAddDoc(accBo));
			if (addFlag == true) {
				addNum = 1;
			}
		} catch (SQLException e) {
			addNum = -1;
			log.error(e.getMessage());
			throw new SQLException(e.getMessage());
		} catch (Exception e) {
			addNum = -1;
			log.error(e.getMessage());
			throw new SQLException(e.getMessage());
		} finally {

				dbUtil.close();
			
		}
    	return addNum;
    }
    /******************************************************
	 * @docRoot   添加附件分类
	 * @author 	  freewind
	 * @param	  aTypeBo 附件分类详细信息
	 * @return	  -1 添加失败 1 添加成功
	 * @exception SQLException(数据库异常)
	 *******************************************************/
    public int addType(AccessoryTypeBo aTypeBo) throws SQLException {
    	int addNum = 0;
    	DBEntity dbUtil = new DBEntity();
    	try {
			boolean addFlag = DBEntity.execute(makeAddType(aTypeBo));
			if (addFlag == true) {
				addNum = 1;
			}
		} catch (SQLException e) {
			addNum = -1;
			log.error(e.getMessage());
			throw new SQLException(e.getMessage());
		} catch (Exception e) {
			addNum = -1;
			log.error(e.getMessage());
			throw new SQLException(e.getMessage());
		} finally {

				dbUtil.close();
			
		}
    	return addNum;
    }
    /******************************************************
	 * @docRoot   删除附件分类
	 * @author 	  freewind
	 * @param	  autoID 附件分类ID
	 * @return	  -1 删除失败 1 删除成功
	 * @exception SQLException(数据库异常)
	 *******************************************************/
    public int delType(String autoID) throws SQLException {
    	int delNum = 0;
    	DBEntity dbUtil = new DBEntity();
    	try {
    		boolean delFlag = DBEntity.execute(makeDelType(autoID));
    		if (delFlag == true) {
    			delNum = 1;
    		}
		} catch (SQLException e) {
			delNum = -1;
			log.error(e.getMessage());
			throw new SQLException(e.getMessage());
		} catch (Exception e) {
			delNum = -1;
			log.error(e.getMessage());
			throw new SQLException(e.getMessage());
		} finally {

				dbUtil.close();
			
		}
    	return delNum;
    }
    /******************************************************
	 * @docRoot   删除附件(多个)
	 * @author 	  freewind
	 * @param	  delIDArr 附件分类ID数组
	 * @return	  -1 删除失败 1 删除成功
	 * @exception SQLException(数据库异常)
	 *******************************************************/
    public int delDocList(String delIDArr, String dirPath) throws SQLException {
    	int delNum = 0;
    	DBEntity dbUtil = new DBEntity();
    	ResultSet rs = null;
    	String[] idArr = delIDArr.split(",");
    	try {
    		for (int i = 0; i < idArr.length; i++) {
	    		rs = dbUtil.executeQuery(makeGetFileName(idArr[i]));
	    		rs.first();
	    		String fileName = rs.getString(AccessoryCommon.caFilePath);
	    		String wholeName = dirPath + fileName;
	    		File fileUtil = new File(wholeName);
	    		fileUtil.delete();
    		}
    		boolean delFlag = DBEntity.execute(makeDelDocArr(delIDArr));
    		if (delFlag == true) {
    			delNum = 1;
    		}
		} catch (SQLException e) {
			delNum = -1;
			log.error(e.getMessage());
			throw new SQLException(e.getMessage());
		} catch (Exception e) {
			delNum = -1;
			log.error(e.getMessage());
			throw new SQLException(e.getMessage());
		} finally {

				rs = null;
				dbUtil.close();
			
		}
    	return delNum;
    }
    /******************************************************
	 * @docRoot   根据owner, ownerID, ownerLevel, catalogID
	 * 			　查询数据库取出附件信息
	 * @author 	  freewind
	 * @param	  owner, ownerID, ownerLevel, catalogID
	 * @return	  附件数组
	 * @exception SQLException(数据库异常)
	 *******************************************************/
    public ArrayList findSearchList(String owner, String ownerID, String ownerLevel, String catalogID) 
    throws SQLException {
    	DBEntity dbUtil = new DBEntity();
    	ResultSet rs = null;
    	ArrayList dataList = new ArrayList();
    	try {
    		String str = makeSearchDocList(owner, ownerID, ownerLevel,catalogID);
			rs = dbUtil.executeQuery(makeSearchDocList(owner, ownerID, ownerLevel,catalogID));
            while (rs.next()) {
				AttachmentBean aBean = new AttachmentBean();
				aBean.setAutoID(rs.getString(AccessoryCommon.caAutoID));
				aBean.setOwner(rs.getString(AccessoryCommon.caOwner));
				aBean.setOwnerID(rs.getString(AccessoryCommon.caOwnerID));
				aBean.setOwnerLevel(rs.getString(AccessoryCommon.caOwnerLevel));
				aBean.setTitle(rs.getString(AccessoryCommon.caTitle));
				aBean.setFilepath(rs.getString(AccessoryCommon.caFilePath));
				aBean.setUserID(rs.getString(AccessoryCommon.caUserID));
				aBean.setInputDate(rs.getString("CINPUTDATE"));
				aBean.setCatalogID(rs.getString(AccessoryCommon.caCatalogID));
				aBean.setDocSize(rs.getString(AccessoryCommon.caDocSize));
				dataList.add(aBean);
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
			throw new SQLException(e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new SQLException(e.getMessage());
		} finally {
			try {
				rs.close();
				dbUtil.close();
			} catch (SQLException e) {
				rs = null;
				dbUtil = null;
			} catch (Exception e) {
				rs = null;
				dbUtil = null;
			}
		}
    	return dataList;
    }
    private String makeDelDocArr(String delIDArr) {
    	StringBuffer sf = new StringBuffer();
    	sf.append("DELETE ");
    	sf.append(AccessoryCommon.tAttachmentInfo);
    	String[] idArr = delIDArr.split(",");
    	for (int i = 0; i< idArr.length; i++) {
    		if (i == 0) {
    			sf.append(" WHERE ");
    		} else {
    			sf.append(" OR ");
    		}
    		sf.append(AccessoryCommon.caAutoID);
        	sf.append(" = ");
        	sf.append(idArr[i]);
    	}
    	return sf.toString();
    }
    private String makeDelType(String autoID) {
    	StringBuffer sf = new StringBuffer();
    	sf.append("DELETE ");
    	sf.append(AccessoryCommon.tAttachmentCatalogInfo);
    	sf.append(" WHERE ");
    	sf.append(AccessoryCommon.catAutoID);
    	sf.append(" = ");
    	sf.append(autoID);
    	return sf.toString();
    }
    private String makeAddType(AccessoryTypeBo aTypeBo) {
    	StringBuffer sf = new StringBuffer();
    	sf.append("INSERT INTO ");
    	sf.append(AccessoryCommon.tAttachmentCatalogInfo);
    	sf.append(" (");
    	sf.append(AccessoryCommon.catAutoID);
    	sf.append(",");
    	sf.append(AccessoryCommon.catOwner);
    	sf.append(",");
    	sf.append(AccessoryCommon.catOwnerID);
    	sf.append(",");
    	sf.append(AccessoryCommon.catOwnerLevel);
    	sf.append(",");
    	sf.append(AccessoryCommon.catCatalogValue);
    	sf.append(",");
    	sf.append(AccessoryCommon.catFlag);
    	sf.append(") ");
    	sf.append("VALUES");
    	sf.append("(");
    	sf.append(AccessoryCommon.seqType + ".nextval");
    	sf.append(",");
    	sf.append("'" + aTypeBo.getOwner() + "'");
    	sf.append(",");
    	sf.append(aTypeBo.getOwnerID());
    	sf.append(",");
    	sf.append(aTypeBo.getOwnerLevel());
    	sf.append(",");
    	sf.append("'" + aTypeBo.getCatalogValue() + "'");
    	sf.append(",");
    	sf.append(aTypeBo.getFlag());
    	sf.append(")");
    	return sf.toString();
    }
    private String makeAddDoc(AccessoryBo accBo) {
    	StringBuffer sf = new StringBuffer();
    	sf.append("INSERT INTO ");
    	sf.append(AccessoryCommon.tAttachmentInfo);
    	sf.append(" (");
    	sf.append(AccessoryCommon.caAutoID);
    	sf.append(",");
    	sf.append(AccessoryCommon.caOwner);
    	sf.append(",");
    	sf.append(AccessoryCommon.caOwnerID);
    	sf.append(",");
    	sf.append(AccessoryCommon.caOwnerLevel);
    	sf.append(",");
    	sf.append(AccessoryCommon.caTitle);
    	sf.append(",");
    	sf.append(AccessoryCommon.caFilePath);
    	sf.append(",");
    	sf.append(AccessoryCommon.caUserID);
    	sf.append(",");
    	sf.append(AccessoryCommon.caInputDate);
    	sf.append(",");
    	sf.append(AccessoryCommon.caCatalogID);
    	sf.append(",");
    	sf.append(AccessoryCommon.caDocSize);
    	sf.append(") ");
    	sf.append("VALUES");
    	sf.append("(");
    	sf.append(AccessoryCommon.seqDoc + ".nextval");
    	sf.append(",");
    	sf.append("'" + accBo.getOwner() + "'");
    	sf.append(",");
    	sf.append(accBo.getOwnerID());
    	sf.append(",");
    	sf.append(accBo.getOwnerLevel());
    	sf.append(",");
    	sf.append("'" + accBo.getTitle() + "'");
    	sf.append(",");
    	sf.append("'" + accBo.getFilepath() + "'");
    	sf.append(",");
    	sf.append(accBo.getUserID());
    	sf.append(",");
    	sf.append("sysdate");
    	sf.append(",");
    	sf.append(accBo.getCatalogID());
    	sf.append(",");
    	sf.append(accBo.getDocSize());
    	sf.append(")");
    	return sf.toString();
    }
    private String makeDelAcc(String autoID) {
    	StringBuffer sf = new StringBuffer();
    	sf.append("DELETE ");
    	sf.append(AccessoryCommon.tAttachmentInfo);
    	sf.append(" WHERE ");
    	sf.append(AccessoryCommon.caAutoID);
    	sf.append(" = ");
    	sf.append(autoID);
    	return sf.toString();
    }
    private String makeFindAccType(String owner, String ownerID, String ownerLevel, String isMeet) {
    	StringBuffer sf = new StringBuffer();
        sf.append("SELECT");
        sf.append(" ");
        sf.append("*");
        sf.append(" ");
        sf.append("FROM");
        sf.append(" ");
        sf.append(AccessoryCommon.tAttachmentCatalogInfo);
        sf.append(" ");
        sf.append("WHERE");
        sf.append(" ");
        sf.append(AccessoryCommon.catOwner);
        sf.append(" ");
        sf.append("=");
        sf.append(" ");
        sf.append("'" + owner + "'");
        sf.append(" ");
//        if (!"0".equals(isMeet)) {
//	        if (!"null".equals(ownerID)) {
//	        	sf.append("AND");
//		        sf.append(" ");
//	        	sf.append(AccessoryCommon.catOwnerID);
//	            sf.append(" ");
//	            sf.append("=");
//	            sf.append(ownerID);
//	            sf.append(" ");
//	        }
//        }
        if (!"null".equals(ownerLevel)) {
        	sf.append("AND");
            sf.append(" ");
        	sf.append(AccessoryCommon.caOwnerLevel);
            sf.append(" ");
            sf.append(" = ");
            sf.append(ownerLevel);
            sf.append(" ");
        }
        sf.append("ORDER BY");
        sf.append(" ");
        sf.append(AccessoryCommon.catAutoID);
    	return sf.toString();
    }
    private String makeFindAccType2(String owner, String ownerID, String ownerLevel, String isMeet) {
    	StringBuffer sf = new StringBuffer();
        sf.append("SELECT");
        sf.append(" ");
        sf.append("a.*");
        sf.append(",(select count(d.auto_id) from attachment_info d where d.owner='"+owner+"' and d.ownerid="+ownerID+" and d.catalog_id=a.auto_id) as file_count");
        sf.append(" ");
        sf.append("FROM");
        sf.append(" ");
        sf.append(AccessoryCommon.tAttachmentCatalogInfo+" a");
        sf.append(" ");
        sf.append("WHERE");
        sf.append(" ");
        sf.append(AccessoryCommon.catOwner);
        sf.append(" ");
        sf.append("=");
        sf.append(" ");
        sf.append("'" + owner + "'");
        sf.append(" ");
        sf.append("AND");
        sf.append(" ");
        sf.append(AccessoryCommon.caOwnerID);
        sf.append(" ");
        sf.append("=");
        sf.append(ownerID);
        sf.append(" ");
//        if (!"0".equals(isMeet)) {
//	        if (!"null".equals(ownerID)) {
//	        	sf.append("AND");
//		        sf.append(" ");
//	        	sf.append(AccessoryCommon.catOwnerID);
//	            sf.append(" ");
//	            sf.append("=");
//	            sf.append(ownerID);
//	            sf.append(" ");
//	        }
//        }
        if (!"null".equals(ownerLevel)) {
        	sf.append("AND");
            sf.append(" ");
        	sf.append(AccessoryCommon.caOwnerLevel);
            sf.append(" ");
            sf.append(" = ");
            sf.append(ownerLevel);
            sf.append(" ");
        }
        sf.append("ORDER BY");
        sf.append(" ");
        sf.append(AccessoryCommon.catAutoID);
    	return sf.toString();
    }
    private String makeFindAccType2(String ownerID, String ownerLevel, String isMeet) {
    	StringBuffer sf = new StringBuffer();
        sf.append("SELECT");
        sf.append(" ");
        sf.append("a.*");
        sf.append(",(select count(d.auto_id) from attachment_info d where d.ownerid='"+ownerID+"' and d.catalog_id=a.auto_id) as file_count");
        sf.append(" ");
        sf.append("FROM");
        sf.append(" ");
        sf.append(AccessoryCommon.tAttachmentCatalogInfo+" a");
        sf.append(" ");
        sf.append("WHERE");
//        sf.append(" ");
//        sf.append(AccessoryCommon.catOwner);
//        sf.append(" ");
//        sf.append("=");
//        sf.append(" ");
//        sf.append("'" + owner + "'");
//        sf.append(" ");
//        sf.append("AND");
        sf.append(" ");
        sf.append(AccessoryCommon.caOwnerID);
        sf.append(" ");
        sf.append("=");
        sf.append(ownerID);
        sf.append(" ");
//        if (!"0".equals(isMeet)) {
//	        if (!"null".equals(ownerID)) {
//	        	sf.append("AND");
//		        sf.append(" ");
//	        	sf.append(AccessoryCommon.catOwnerID);
//	            sf.append(" ");
//	            sf.append("=");
//	            sf.append(ownerID);
//	            sf.append(" ");
//	        }
//        }
        if (!"null".equals(ownerLevel)) {
        	sf.append("AND");
            sf.append(" ");
        	sf.append(AccessoryCommon.caOwnerLevel);
            sf.append(" ");
            sf.append(" = ");
            sf.append(ownerLevel);
            sf.append(" ");
        }
        sf.append("ORDER BY");
        sf.append(" ");
        sf.append(AccessoryCommon.catAutoID);
    	return sf.toString();
    }
    private String makeFindDocument(String owner, String ownerID, String ownerLevel) {
    	StringBuffer sf= new StringBuffer();
        sf.append("SELECT");
        sf.append(" ");
        sf.append(AccessoryCommon.caAutoID);
        sf.append(",");
        sf.append(AccessoryCommon.caOwner);
        sf.append(",");
        sf.append(AccessoryCommon.caOwnerID);
        sf.append(",");
        sf.append(AccessoryCommon.caOwnerLevel);
        sf.append(",");
        sf.append(AccessoryCommon.caTitle);
        sf.append(",");
//        sf.append(AccessoryCommon.caFilePath);
//        sf.append(",");
        sf.append(AccessoryCommon.caUserID);
        sf.append(",");
        sf.append("TO_CHAR(" + AccessoryCommon.caInputDate + ", 'YYYY-MM-DD') AS CINPUTDATE");
        sf.append(",");
        sf.append(AccessoryCommon.caCatalogID);
        sf.append(",");
        sf.append(AccessoryCommon.caDocSize);
//        sf.append(",");
//        sf.append("orderid");
        sf.append(" ");
        sf.append("FROM");
        sf.append(" ");
        sf.append(AccessoryCommon.tAttachmentInfo);
        sf.append(" ");
        sf.append("WHERE");
        sf.append(" ");
        sf.append(AccessoryCommon.caOwner);
        sf.append(" ");
        sf.append("=");
        sf.append(" ");
        sf.append("'" + owner + "'");
        sf.append(" ");
        if (!"null".equals(ownerID)) {
        	sf.append("AND");
        	sf.append(" ");
	        sf.append(AccessoryCommon.caOwnerID);
	        sf.append(" = '");
	        sf.append(ownerID);
	        sf.append("' ");
        }
        if (!"null".equals(ownerLevel)) {
        	sf.append("AND");
            sf.append(" ");
        	sf.append(AccessoryCommon.caOwnerLevel);
            sf.append(" ");
            sf.append("=");
            sf.append(ownerLevel);
            sf.append(" ");
        }
        sf.append("ORDER BY");
        sf.append(" ");
        sf.append(AccessoryCommon.caCatalogID);
        System.out.println(" 这里是AccessoryService.java     makeFindDocument()    sf.toString()"+sf.toString());
        return sf.toString();
    }
    private String makeFindDocument2(String owner, String ownerID, String ownerLevel) {
    	StringBuffer sf= new StringBuffer();
        sf.append("SELECT");
        sf.append(" ");
        sf.append(AccessoryCommon.caAutoID);
        sf.append(",");
        sf.append(AccessoryCommon.caOwner);
        sf.append(",");
        sf.append(AccessoryCommon.caOwnerID);
        sf.append(",");
        sf.append(AccessoryCommon.caOwnerLevel);
        sf.append(",");
        sf.append(AccessoryCommon.caTitle);
        sf.append(",");
//        sf.append(AccessoryCommon.caFilePath);
//        sf.append(",");
        sf.append(AccessoryCommon.caUserID);
        sf.append(",");
        sf.append("TO_CHAR(" + AccessoryCommon.caInputDate + ", 'YYYY-MM-DD') AS CINPUTDATE");
        sf.append(",");
        sf.append(AccessoryCommon.caCatalogID);
        sf.append(",");
        sf.append(AccessoryCommon.caDocSize);
//        sf.append(",");
//        sf.append("orderid");
        sf.append(" ");
        sf.append("FROM");
        sf.append(" ");
        sf.append(AccessoryCommon.tAttachmentInfo);
        sf.append(" ");
        sf.append("WHERE");
        sf.append(" ");
        sf.append(AccessoryCommon.caOwner);
        sf.append(" ");
        sf.append("=");
        sf.append(" ");
        sf.append("'" + owner + "'");
        sf.append(" ");
        if (!"null".equals(ownerID)) {
        	sf.append("AND");
        	sf.append(" ");
	        sf.append(AccessoryCommon.caOwnerID);
//	        sf.append(" = '");
//	        sf.append(ownerID);
//	        sf.append("' ");
	        sf.append(" in(");
	        sf.append(ownerID);
	        sf.append(") ");
        }
        if (!"null".equals(ownerLevel)) {
        	sf.append("AND");
            sf.append(" ");
        	sf.append(AccessoryCommon.caOwnerLevel);
            sf.append(" ");
            sf.append("=");
            sf.append(ownerLevel);
            sf.append(" ");
        }
        sf.append("ORDER BY");
        sf.append(" ");
        sf.append(AccessoryCommon.caCatalogID);
        System.out.println(" 这里是AccessoryService.java     makeFindDocument()    sf.toString()"+sf.toString());
        return sf.toString();
    }
    private String makeFindDocument3(String ownerID, String ownerLevel) {
    	StringBuffer sf= new StringBuffer();
        sf.append("SELECT");
        sf.append(" ");
        sf.append(AccessoryCommon.caAutoID);
        sf.append(",");
        sf.append(AccessoryCommon.caOwner);
        sf.append(",");
        sf.append(AccessoryCommon.caOwnerID);
        sf.append(",");
        sf.append(AccessoryCommon.caOwnerLevel);
        sf.append(",");
        sf.append(AccessoryCommon.caTitle);
        sf.append(",");
//        sf.append(AccessoryCommon.caFilePath);
//        sf.append(",");
        sf.append(AccessoryCommon.caUserID);
        sf.append(",");
        sf.append("TO_CHAR(" + AccessoryCommon.caInputDate + ", 'YYYY-MM-DD') AS CINPUTDATE");
        sf.append(",");
        sf.append(AccessoryCommon.caCatalogID);
        sf.append(",");
        sf.append(AccessoryCommon.caDocSize);
//        sf.append(",");
//        sf.append("orderid");
        sf.append(" ");
        sf.append("FROM");
        sf.append(" ");
        sf.append(AccessoryCommon.tAttachmentInfo);
        sf.append(" ");
        sf.append("WHERE");
        sf.append(" ");
//        sf.append(AccessoryCommon.caOwner);
//        sf.append(" ");
//        sf.append("=");
//        sf.append(" ");
//        sf.append("'" + owner + "'");
//        sf.append(" ");
        if (!"null".equals(ownerID)) {
//        	sf.append("AND");
        	sf.append(" ");
	        sf.append(AccessoryCommon.caOwnerID);
	        sf.append(" = '");
	        sf.append(ownerID);
	        sf.append("' ");
        }
        if (!"null".equals(ownerLevel)) {
        	sf.append("AND");
            sf.append(" ");
        	sf.append(AccessoryCommon.caOwnerLevel);
            sf.append(" ");
            sf.append("=");
            sf.append(ownerLevel);
            sf.append(" ");
        }
        sf.append("ORDER BY");
        sf.append(" ");
        sf.append(AccessoryCommon.caCatalogID);
        System.out.println(" 这里是AccessoryService.java     makeFindDocument()    sf.toString()"+sf.toString());
        return sf.toString();
    }
    //owner,ownerID不能为空，具体附件应该落到项目上.
    private String makeSearchDocList(String owner, String ownerID, String ownerLevel, String catalogID) {
    	StringBuffer sf= new StringBuffer();
        sf.append("SELECT");
        sf.append(" ");
        sf.append(AccessoryCommon.caAutoID);
        sf.append(",");
        sf.append(AccessoryCommon.caOwner);
        sf.append(",");
        sf.append(AccessoryCommon.caOwnerID);
        sf.append(",");
        sf.append(AccessoryCommon.caOwnerLevel);
        sf.append(",");
        sf.append(AccessoryCommon.caTitle);
        sf.append(",");
        sf.append(AccessoryCommon.caFilePath);
        sf.append(",");
        sf.append(AccessoryCommon.caUserID);
        sf.append(",");
        sf.append("TO_CHAR(" + AccessoryCommon.caInputDate + ", 'YYYY-MM-DD') AS CINPUTDATE");
        sf.append(",");
        sf.append(AccessoryCommon.caCatalogID);
        sf.append(",");
        sf.append(AccessoryCommon.caDocSize);
        sf.append(" ");
        sf.append("FROM");
        sf.append(" ");
        sf.append(AccessoryCommon.tAttachmentInfo);
        sf.append(" WHERE ");
        sf.append(AccessoryCommon.caOwner);
        sf.append(" ");
        sf.append("=");
        sf.append(" ");
        sf.append("'" + owner + "'");
        sf.append(" ");
        sf.append("AND");
        sf.append(" ");
        sf.append(AccessoryCommon.caOwnerID);
        sf.append(" ");
        sf.append("=");
        sf.append(ownerID);
        if (ownerLevel != null && !"".equals(ownerLevel.trim())) {
        	sf.append(" AND ");
        	sf.append(AccessoryCommon.caOwnerLevel);
        	sf.append(" = ");
            sf.append(ownerLevel);
		}
        if (catalogID != null && !"".equals(catalogID.trim())) {
        	sf.append(" AND ");
        	sf.append(AccessoryCommon.caCatalogID);
        	sf.append(" = ");
            sf.append(catalogID);
		}
        sf.append(" ORDER BY ");
        sf.append(AccessoryCommon.caAutoID);
    	return sf.toString();
    }
    private String makeGetFileName(String autoID) {
    	StringBuffer sf= new StringBuffer();
        sf.append("SELECT");
        sf.append(" ");
        sf.append(AccessoryCommon.caFilePath);
        sf.append(" ");
        sf.append("FROM");
        sf.append(" ");
        sf.append(AccessoryCommon.tAttachmentInfo);
        sf.append(" WHERE ");
        sf.append(AccessoryCommon.caAutoID);
        sf.append(" ");
        sf.append("=");
        sf.append(" ");
        sf.append(autoID);
    	return sf.toString();
    }
    
    /**
     * 修改
     * @param owerid
     * @param flowno
     */
    public int updateOwerId(long owerid,long flowno){
    	String sql="update ATTACHMENT_INFO set OWNERID='"+owerid+"' where OWNERID='"+flowno+"'";
    	int addNum=0;
    	DBEntity dbUtil = new DBEntity();
    	try {
			boolean addFlag = DBEntity.execute(sql);
			if (addFlag == true) {
				addNum = 1;
			}
		} catch (SQLException e) {
			addNum = -1;
			log.error(e.getMessage());
			try {
				throw new SQLException(e.getMessage());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			addNum = -1;
			log.error(e.getMessage());
			try {
				throw new SQLException(e.getMessage());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
				dbUtil.close();
		}
    	return addNum;
    }
    
    /**
     * 修改
     * @param owerid
     * @param flowno
     */
    public int updateOwerId(String owerid,long flowno){
    	String sql="update ATTACHMENT_INFO set OWNERID='"+owerid+"' where OWNERID='"+flowno+"'";
    	int addNum=0;
    	DBEntity dbUtil = new DBEntity();
    	try {
			boolean addFlag = DBEntity.execute(sql);
			if (addFlag == true) {
				addNum = 1;
			}
		} catch (SQLException e) {
			addNum = -1;
			log.error(e.getMessage());
			try {
				throw new SQLException(e.getMessage());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			addNum = -1;
			log.error(e.getMessage());
			try {
				throw new SQLException(e.getMessage());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
				dbUtil.close();
		}
    	return addNum;
    }
}
