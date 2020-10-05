package smart.app.common;

import java.sql.*;

import smart.css.database.*;
import java.util.*;

public class Common {
	private int pageCount;
	private int count;
	private long autoID;
	private String title;

	public long getAutoID() {
		return autoID;
	}

	public void setAutoID(long autoID) {
		this.autoID = autoID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Common() {
	}

	/*
	 * ���ݴ����ID�ַ���(��ʽ��id1,id2,id3)�õ���Ӧ�ı���ߵĶ�Ӧ��������
	 * @return String
	 */
	public String getNameByIdStr(String str, String talbeName, String keyWords) {
		String strs[] = null;
		String sql = "";
		strs = str.split(",");
		String Name = "";
		smart.css.database.DBOperator dbo = new smart.css.database.DBOperator();
		try {
			for (int i = 0; i < strs.length; i++) {
				sql = "select " + keyWords + " from " + talbeName
						+ " where auto_id = " + strs[i];

				Name += "," + dbo.getSingleValue(sql).toString();
			}
			if (Name.length() > 0) {
				Name = Name.substring(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbo.close();
		}
		return Name;
	}

	public String[][] getAllList(String sql, int page, int pagesize, int num) {
		String[][] mCollection = null;
		try {
			smart.css.database.DBOperator dbo = new smart.css.database.DBOperator();
			try {
				Vector v_model = new Vector(dbo.executeQuery(sql, page,
						pagesize));
				this.count = dbo.getCountValue(sql);
				if (this.count == 0) {
					return mCollection;
				} else if (this.count % pagesize == 0) {
					this.pageCount = this.count / pagesize;
				} else {
					this.pageCount = this.count / pagesize + 1;
				}
				mCollection = new String[v_model.size()][num];
				for (int i = 0; i < v_model.size(); i++) {
					for (int j = 0; j < num; j++) {
						mCollection[i][j] = ((Vector) v_model.elementAt(i))
								.elementAt(j).toString();
					}
				}
				v_model = null;
			} catch (Exception ex) {
				ex.printStackTrace();

			} finally {
				dbo.close();

			}
		} catch (Exception ex) {

		}

		return mCollection;
	}

	/*
	 * 
	 * 
	 */
	public String[][] getWholeList(String sql, int num) {
		String[][] mCollection = null;
		ResultSet rs = null;
		try {
			smart.css.database.DBOperator dbo = new smart.css.database.DBOperator();
			try {
				this.count = dbo.getCountValue(sql);
				if (this.count == 0) {
					return mCollection;
				}
				mCollection = new String[count][num];
				rs = dbo.executeQuery(sql);
				int i = 0;
				int k = 0;
				while (rs.next()) {
					for (int j = 0; j < num; j++) {
						k = j + 1;
						if (rs.getString(k) != null) {
							mCollection[i][j] = rs.getString(k);
						} else {
							mCollection[i][j] = "";
						}
					}
					i++;

				}
			} catch (Exception ex) {
				ex.printStackTrace();

			} finally {
				dbo.close();
				rs.close();
			}
		}

		catch (Exception ex) {

		}

		return mCollection;
	}

	public Common[] getDictionaryList(String sql) {
		smart.css.database.DBOperator dbo = new smart.css.database.DBOperator();
		Common[] pList = null;
		ResultSet rsList = null;
		int i = 0;
		try {

			int count = dbo.getCountValue(sql);
			if (count == 0)
				return pList;
			//ʵ���������������Ĵ�С
			pList = new Common[count];
			rsList = dbo.executeQuery(sql);
			//ѭ��Ϊ����������鸳ֵ
			while (rsList.next()) {
				long l = rsList.getLong("auto_id");
				pList[i] = new Common();
				pList[i].setAutoID(rsList.getLong("auto_id"));
				pList[i].setTitle(rsList.getString("title"));
				i++;
			}
		} catch (Exception ex) {
			pList = null;
			System.err.println(ex.getMessage());
		} finally {
			//���еĹرձ�����finally��ִ��
			try {
				rsList.close();
				dbo.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return pList;

	}

	public Common[] getDictionaryList(String sql, String id, String name) {
		smart.css.database.DBOperator dbo = new smart.css.database.DBOperator();
		Common[] pList = null;
		ResultSet rsList = null;
		int i = 0;
		try {

			int count = dbo.getCountValue(sql);
			if (count == 0)
				return pList;
			//ʵ���������������Ĵ�С
			pList = new Common[count];
			rsList = dbo.executeQuery(sql);
			//ѭ��Ϊ����������鸳ֵ
			while (rsList.next()) {
				long l = rsList.getLong("auto_id");
				pList[i] = new Common();
				pList[i].setAutoID(rsList.getLong(id));
				pList[i].setTitle(rsList.getString(name));
				i++;
			}
		} catch (Exception ex) {
			pList = null;
			System.err.println(ex.getMessage());
		} finally {
			//���еĹرձ�����finally��ִ��
			try {
				if (rsList != null) {
					rsList.close();
				}
				dbo.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return pList;

	}

	/**
	 * �õ����ż���id��auto_id,title����
	 * @return String[][]
	 */
	public String[][] getDepList() {
		String[][] list = null;
		smart.css.database.DBOperator dbo = new smart.css.database.DBOperator();
		ResultSet rs = null;
		String sql = "";
		int count = 0;
		int i = 0;
		sql = "select auto_id,title from org_tree where parent_id=0";
		try {
			count = dbo.getCountValue(sql); //�õ�������
			list = new String[count][2];
			rs = dbo.executeQuery(sql);
			while (rs.next()) {
				list[i][0] = rs.getString("auto_id");
				list[i][1] = rs.getString("title");
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//ע�����еĹرձ�����finally��ִ��
			try {
				dbo.close(rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public String getDate(String year, String month, String flag) {
		String dt = "";
		if (year.equals("")) {
			return dt;
		}
		if (month.trim().equals("")) {
			if (flag.trim().equals("0")) {
				dt = year.trim() + "-1-1";
			} else if (flag.equals("1")) {
				dt = year.trim() + "12-31";
			}
		} else {
			if (flag.trim().equals("0")) {
				dt = year.trim() + "-" + month.trim() + "-1";
			} else if (flag.equals("1")) {
				if (month.trim().equals("2")) {
					dt = year.trim() + "-" + month.trim() + "-28";
				} else if (month.trim().equals("1") || month.trim().equals("3")
						|| month.trim().equals("5") || month.trim().equals("7")
						|| month.trim().equals("8")
						|| month.trim().equals("10")
						|| month.trim().equals("12")) {
					dt = year.trim() + "-" + month.trim() + "-31";
				} else {
					dt = year.trim() + "-" + month.trim() + "-30";
				}
			}
		}

		return dt;
	}

	public HashMap getDeepHash(String treeID, String tableName) {
		HashMap hm = new HashMap();
		smart.css.database.DBOperator dbo = new smart.css.database.DBOperator();
		ResultSet rs = null;
		String sql = "";
		String parentID = "";
		int deeps = 0;
		int count = 0;
		int i = 0;
		sql = "select deep from " + tableName + " where auto_id =" + treeID;
		try {
			parentID = treeID;
			deeps = Integer.parseInt(dbo.getSingleValue(sql).toString());
			hm.put("deep" + (deeps - 1), treeID);
			for (int j = deeps; j > 1; j--) {
				parentID = dbo.getSingleValue(
						"select parent_id from " + tableName
								+ " where auto_id =" + parentID).toString();
				deeps = j - 2;
				hm.put("deep" + deeps, parentID);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//ע�����еĹرձ�����finally��ִ��
			try {
				dbo.close(rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return hm;
	}

	/**
	 * �õ�������
	 * @sql �õ��б��SQL��name �������ID��id ֵ��Ӧ���ֶΣ�title���ݶ�Ӧ���ֶΣ�defĬ��ֵ��û��Ĭ��ֵʱΪ0
	 ������ӵ�
	 */

	public String getDropListo(String sql, String name, String id,
			String title, String def) {
		String result = "";
		ResultSet rs = null;
		int i = 0;
		int j = 0;
		try {
			smart.css.database.DBOperator dbo = new smart.css.database.DBOperator();
			result = result + "\n" + "<option value=0>=\u8bf7\u9009\u62e9=</option>";
			System.out.println(sql);
			try {
				rs = dbo.executeQuery(sql);
				//			System.out.println(sql);
				while (rs.next()) {
					if (rs.getString(id).equals(def)) {
						result = result + "\n" + "<option value="
								+ rs.getString(id) + " selected>"
								+ rs.getString(title) + "</option>";
					} else {
						result = result + "\n" + "<option value="
								+ rs.getString(id) + ">" + rs.getString(title)
								+ "</option>";
					}
				}
			} catch (Exception e) {

			} finally {
				try {
					rs.close();
					dbo.close();
				} catch (SQLException e) {
					rs = null;
					dbo = null;
				} catch (Exception e) {
					rs = null;
					dbo = null;
				}
			}
		} catch (Exception e) {

		}
		return result;
	}

	/**
	 * �õ�������
	 * @sql �õ��б��SQL��name �������ID��id ֵ��Ӧ���ֶΣ�title���ݶ�Ӧ���ֶΣ�defĬ��ֵ��û��Ĭ��ֵʱΪ0
	 ������ӵ�
	 */

	public String getDropListForAddNum(String sql, String name, String id,
			String title, String def) {
		String result = "";
		ResultSet rs = null;
		int i = 0;
		int j = 0;
		try {
			smart.css.database.DBOperator dbo = new smart.css.database.DBOperator();
			result = result + "\n" + "<option value=0>��ѡ��</option>";
			try {
				rs = dbo.executeQuery(sql);
				while (rs.next()) {
					if (rs.getString(title).equals(def)) {
						result = result + "\n" + "<option value="
								+ rs.getString(id) + " selected>"
								+ rs.getString(id) + "(" + rs.getString(title)
								+ ")</option>";
					} else {
						result = result + "\n" + "<option value="
								+ rs.getString(id) + ">" + rs.getString(id)
								+ "(" + rs.getString(title) + ")</option>";
					}
				}
			} catch (Exception e) {

			} finally {
				try {
					rs.close();
					dbo.close();
				} catch (SQLException e) {
					rs = null;
					dbo = null;
				} catch (Exception e) {
					rs = null;
					dbo = null;
				}
			}
		} catch (Exception e) {

		}
		return result;
	}

	/**
	 * �õ�������
	 * @sql �õ��б��SQL��name �������ID��id ֵ��Ӧ���ֶΣ�title���ݶ�Ӧ���ֶΣ�defĬ��ֵ��û��Ĭ��ֵʱΪ0
	 ���ڷ�����ʾ��
	 */

	public String getDropLista(String sql, String name, String id,
			String title, String def) {
		String phone="";
		String result = "";
		ResultSet rs = null;
		int i = 0;
		int j = 0;
		try {
			smart.css.database.DBOperator dbo = new smart.css.database.DBOperator();
			try {
				rs = dbo.executeQuery(sql);
				System.out.println(sql);
				while (rs.next()) {
					if (rs.getString(id).equals(def)) {
						result = result + "\n" + "<option value="
								+ rs.getString(id) +","+rs.getString(phone)+ " selected>"
								+ rs.getString(title) + "</option>";
					} else {
						result = result + "\n" + "<option value="
								+ rs.getString(id)+ ">" + rs.getString(title)
								+ "</option>";
					}
				}
			} catch (Exception e) {
			} finally {
				try {
					rs.close();
					dbo.close();
				} catch (SQLException e) {
					rs = null;
					dbo = null;
				} catch (Exception e) {
					rs = null;
					dbo = null;
				}
			}
			//result = result + "</select>";
		} catch (Exception e) {

		}
		return result;
	}

	///���������Ա
	public String getAllUser(String sql, String name, String id, String title,
			String def) {
		String result = "";
		ResultSet rs = null;

		try {
			smart.css.database.DBOperator dbo = new smart.css.database.DBOperator();
			try {
				rs = dbo.executeQuery(sql);
				System.out.println(sql);
				while (rs.next()) {
					result = result + rs.getString(id) + ",";
				}
			} catch (Exception e) {

			} finally {
				try {
					rs.close();
					dbo.close();
				} catch (SQLException e) {
					rs = null;
					dbo = null;
				} catch (Exception e) {
					rs = null;
					dbo = null;
				}
			}
		} catch (Exception e) {

		}
		result = result.substring(0, result.length() - 1);
		return result;
	}

	public String getAllUserName(String sql, String name, String id,
			String title, String def) {
		String result = "";
		ResultSet rs = null;

		try {
			smart.css.database.DBOperator dbo = new smart.css.database.DBOperator();
			try {
				rs = dbo.executeQuery(sql);
				System.out.println(sql);
				while (rs.next()) {
					result = result + rs.getString(title) + ",";
				}
			} catch (Exception e) {

			} finally {
				try {
					rs.close();
					dbo.close();
				} catch (SQLException e) {
					rs = null;
					dbo = null;
				} catch (Exception e) {
					rs = null;
					dbo = null;
				}
			}
		} catch (Exception e) {

		}
		result = result.substring(0, result.length() - 1);
		return result;
	}

	public String getDropListoShow(String sql, String name, String id,
			String title, String def) {
		String result = "";
		ResultSet rs = null;
		int i = 0;
		int j = 0;
		try {
			smart.css.database.DBOperator dbo = new smart.css.database.DBOperator();

			//result = "<select name=" + name + ">";

			result = result + "\n" + "<option value=0>ȫ��</option>";
			//System.out.println(sql);
			// System.out.println(id);
			// System.out.println(title);
			try {
				rs = dbo.executeQuery(sql);
				System.out.println(sql);
				while (rs.next()) {
					if (rs.getString(id).equals(def)) {
						result = result + "\n" + "<option value="
								+ rs.getString(id) + " selected>"
								+ rs.getString(title) + "</option>";
					} else {
						result = result + "\n" + "<option value="
								+ rs.getString(id) + ">" + rs.getString(title)
								+ "</option>";
					}
				}
			} catch (Exception e) {

			} finally {
				try {
					rs.close();
					dbo.close();
				} catch (SQLException e) {
					rs = null;
					dbo = null;
				} catch (Exception e) {
					rs = null;
					dbo = null;
				}
			}
			//result = result + "</select>";
		} catch (Exception e) {

		}
		return result;
	}

	public String getDropList(String sql, String name, String id, String title,
			String def) {
		String result = "";
		ResultSet rs = null;
		int i = 0;
		int j = 0;
		try {
			smart.css.database.DBOperator dbo = new smart.css.database.DBOperator();

			result = "<select name=" + name + ">";

			result = result + "\n" + "<option value=0>��ѡ��</option>";
			//System.out.println(sql);
			// System.out.println(id);
			// System.out.println(title);
			try {
				rs = dbo.executeQuery(sql);
				while (rs.next()) {
					if (rs.getString(id).equals(def)) {
						result = result + "\n" + "<option value="
								+ rs.getString(id) + " selected>"
								+ rs.getString(title) + "</option>";
					} else {
						result = result + "\n" + "<option value="
								+ rs.getString(id) + ">" + rs.getString(title)
								+ "</option>";
					}
				}
			} catch (Exception e) {

			} finally {
				try {
					rs.close();
					dbo.close();
				} catch (SQLException e) {
					rs = null;
					dbo = null;
				} catch (Exception e) {
					rs = null;
					dbo = null;
				}
			}
			result = result + "</select>";
		} catch (Exception e) {

		}
		return result;
	}

	/**
	 * �õ�������
	 * @sql �õ��б��SQL��name �������ID��id ֵ��Ӧ���ֶΣ�title���ݶ�Ӧ���ֶΣ�defĬ��ֵ��û��Ĭ��ֵʱΪ0
	 */
	public String getFunctionDropList(String sql, String name, String id,
			String title, String def, String func) {
		String result = "";
		ResultSet rs = null;
		int i = 0;
		int j = 0;
		try {
			smart.css.database.DBOperator dbo = new smart.css.database.DBOperator();

			result = "<select name=" + name + "  onchange=\"" + func + "();\">";

			result = result + "\n" + "<option value=0>��ѡ��</option>";

			try {
				rs = dbo.executeQuery(sql);
				while (rs.next()) {
					if (rs.getString(id).equals(def)) {
						result = result + "\n" + "<option value="
								+ rs.getString(id) + " selected>"
								+ rs.getString(title) + "</option>";
					} else {
						result = result + "\n" + "<option value="
								+ rs.getString(id) + ">" + rs.getString(title)
								+ "</option>";
					}
				}
			} catch (Exception e) {

			} finally {
				try {
					rs.close();
					dbo.close();
				} catch (SQLException e) {
					rs = null;
					dbo = null;
				} catch (Exception e) {
					rs = null;
					dbo = null;
				}
			}
			result = result + "</select>";
		} catch (Exception e) {

		}
		return result;
	}

	/**
	 * �õ�������
	 * @sql �õ��б��SQL��name �������ID��id ֵ��Ӧ���ֶΣ�title���ݶ�Ӧ���ֶ�
	 */
	public String getOnChangeDropList(String input, String keyWord,
			String tableName) {
		String result = "";
		String selectName = "";
		String fuction = "";
		if (input == null || input.equals("")) {
			return result;
		}
		selectName = input + "select";
		fuction = input + "function";
		ResultSet rs = null;
		int i = 0;
		int j = 0;
		String sql = "";
		try {
			smart.css.database.DBOperator dbo = new smart.css.database.DBOperator();

			result = "<script >function " + fuction
					+ "(){if(document.getElementById(\"" + selectName
					+ "\").selectedIndex!=0){document.getElementById(\""
					+ input + "\").value=document.getElementById(\""
					+ selectName + "\").item(document.getElementById(\""
					+ selectName + "\").selectedIndex).text;}}</script>";
			result = result + "<select name=\"" + selectName + "\"  name=\""
					+ selectName + "\"  onchange=\"" + fuction + "()\" >";

			result = result + "\n" + "<option value=0>��ѡ��</option>";
			sql = "select distinct(" + keyWord + ") from " + tableName
					+ "  where " + keyWord + " is not null";
			try {
				rs = dbo.executeQuery(sql);
				while (rs.next()) {

					result = result + "\n" + "<option >" + rs.getString(1)
							+ "</option>";
				}
			} catch (Exception e) {

			} finally {
				try {
					rs.close();
					dbo.close();
				} catch (SQLException e) {
					rs = null;
					dbo = null;
				} catch (Exception e) {
					rs = null;
					dbo = null;
				}
			}
			result = result + "</select>";
		} catch (Exception e) {

		}
		return result;
	}

	/**
	 * �õ�������
	 * @sql �õ��б��SQL��name �������ID��id ֵ��Ӧ���ֶΣ�title���ݶ�Ӧ���ֶΣ�defĬ��ֵ��û��Ĭ��ֵʱΪ0
	 */
	public String getNodefDropList(String sql, String name, String id,
			String title) {
		String result = "";
		ResultSet rs = null;
		int i = 0;
		int j = 0;
		try {
			smart.css.database.DBOperator dbo = new smart.css.database.DBOperator();

			result = "<select name=" + name + ">";

			try {
				rs = dbo.executeQuery(sql);
				while (rs.next()) {

					result = result + "\n" + "<option value="
							+ rs.getString(id) + ">" + rs.getString(title)
							+ "</option>";
				}
			} catch (Exception e) {

			} finally {
				try {
					rs.close();
					dbo.close();
				} catch (SQLException e) {
					rs = null;
					dbo = null;
				} catch (Exception e) {
					rs = null;
					dbo = null;
				}
			}
			result = result + "</select>";
		} catch (Exception e) {

		}
		return result;
	}

	/**
	   @ ��ò�ѯ����ѯ����ĸ���.
	   @ Param: SQL
	   @ Exception: SQLException
	   @ return int:count
	   @ see\u00A3\u00BA
	 **/
	public static int getSumValue(String sql, String str) throws SQLException,
			Exception {
		int sum_of_query = 0;
		Statement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			smart.css.database.DBOperator dbo = new smart.css.database.DBOperator();
			int a = sql.indexOf("from");
			sql = sql.substring(a, sql.length());
			sql = "select sum(" + str + ") as sum_of_query " + sql;
			try {
				conn = dbo.getConnection();
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					sum_of_query = rs.getInt("sum_of_query");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				dbo.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}

			if (conn != null) {
				conn.close();
				conn = null;
			}
		}
		return sum_of_query;
	}

	/**
	 * �õ��û�auto_id,title���б�
	 * @param treeid ����/��ڵ�
	 * @return String[][]
	 */
	public String[][] getUserList(long depID) {
		String[][] list = null;
		ResultSet rs = null;
		String sql = "";
		int count = 0;
		int i = 0;
		smart.css.database.DBOperator dbe = new smart.css.database.DBOperator();
		sql = "select auto_id,name from org_user where department_id=" + depID
				+ " order by sequence";
		try {
			//�ó�����ĳ���
			count = dbe.getCountValue(sql);
			list = new String[count][2];
			rs = dbe.executeQuery(sql);
			while (rs.next()) {
				list[i][0] = rs.getString("auto_id");
				list[i][1] = rs.getString("name");
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//ע�����еĹرձ�����finally��ִ��
			try {
				dbe.close(rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * �õ������û�������
	 * @sql formָ����������form,depDropListID�����������name,userDropListID�û��������name
	 */
	public String getDepUserDrop(String form, String depDropListID,
			String userDropListID) {
		String result = "";
		ResultSet rs = null;
		int i = 0;
		int j = 0;
		String[][] depIDList = this.getDepList(); //ȡ�ò��ż�����id�б�
		//out.print(depIDList.length);
		String[][] userIDList = null; //�û�id�б�
		try {
			smart.css.database.DBOperator dbo = new smart.css.database.DBOperator();

			try {
				result += "\n" + "<script language=javascript>";
				result += "\n" + "function " + depDropListID + "_onchange()";
				result += "\n" + "{";
				result += "\n"
						+ "	var oOption;var length;var side;var i;var num;";
				result += "\n" + "	num=document." + form + "." + depDropListID
						+ ".selectedIndex;";
				result += "\n" + "	side=document." + form + "." + depDropListID
						+ ".options[num].value;"; //�õ�ѡ�еĲ��ŵ�id��

				for (int r = 0; r < depIDList.length; r++) {
					//ȡ�����еĲ��ŵ�id
					//�����еĲ���id����ѡ�е�id���Ƚϣ���������ȡ�����еĸò��ŵ��û�id
					result += "\n" + "if (side==" + "\"" + depIDList[r][0]
							+ "\"" + ")";
					result += "\n" + "{";

					userIDList = this.getUserList(Long
							.parseLong(depIDList[r][0]));
					result += "\n" + "		for(var x=document." + form + "."
							+ userDropListID + ".length-1;x>=0;x--){";
					result += "\n" + "			document." + form + "."
							+ userDropListID + ".options[x] = null;";
					result += "\n" + "		}";
					for (int u = 0; u < userIDList.length; u++) {
						result += "\n" + "		oOption=document.createElement("
								+ "\"" + "OPTION" + "\"" + ");";
						result += "\n" + "		oOption.text=" + "\""
								+ userIDList[u][1] + "\"" + ";";
						result += "\n" + "		oOption.value=" + "\""
								+ userIDList[u][0] + "\"" + ";";
						result += "\n" + "		document." + form + "."
								+ userDropListID + ".add(oOption);";

					}
					result += "\n" + "	}";
				}
				result += "\n" + "	}";
				result += "\n" + "</script>";

				//new
				result += "\n" + "<select name=" + depDropListID + " id="
						+ depDropListID
						+ "   LANGUAGE=javascript onchange='return  "
						+ depDropListID + "_onchange()'>";
				result += "\n" + "<option value=0><--��ѡ����--></option>";
				for (i = 0; i < depIDList.length; i++) {
					result += "\n" + "<option value=" + depIDList[i][0] + ">"
							+ depIDList[i][1] + "</option>";
				}
				result += "\n" + "</select>";
				result += "\n" + "<select name=" + userDropListID + " id="
						+ userDropListID + " >";
				result += "\n" + "<option value=0><--��ѡ���û�--></option>";
				userIDList = this.getUserList(Long.parseLong(depIDList[0][0]));
				for (i = 0; i < userIDList.length; i++) {
					result += "\n" + "<option value=" + userIDList[i][0] + " >"
							+ userIDList[i][1] + "</option>";
				}
				result += "\n" + "</select>";
			} catch (Exception e) {

			} finally {
				dbo.close();
			}
		} catch (Exception e) {

		}
		return result;
	}

	/**
	 * ͨ�����ŵ�id�ĵ����ŵ�����
	 * @param autoID
	 * @return String
	 */
	public static long getDepIDbyUID(long userID) {
		long depID = 0;
		String sql = "";
		ResultSet rs = null;
		sql = "select department_id from org_user where auto_id=" + userID + "";
		smart.css.database.DBOperator dbo = new smart.css.database.DBOperator();
		try {
			rs = dbo.executeQuery(sql);
			if (rs.next()) {
				depID = rs.getLong("department_id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//ע�����еĹرձ�����finally��ִ��
			try {
				dbo.close(rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return depID;
	}

	/**
	 * �õ������û�������
	 * @sql formָ����������form,depDropListID�����������name,userDropListID�û��������name
	 */
	public String getDefDepUserDrop(String form, String depDropListID,
			String userDropListID, long uID) {
		String result = "";
		ResultSet rs = null;
		int i = 0;
		int j = 0;
		long depID = this.getDepIDbyUID(uID);
		String[][] depIDList = this.getDepList(); //ȡ�ò��ż�����id�б�
		//out.print(depIDList.length);
		String[][] userIDList = null; //�û�id�б�
		try {
			smart.css.database.DBOperator dbo = new smart.css.database.DBOperator();

			try {
				result += "<script language=javascript>";
				result += "function " + depDropListID + "_onchange()";
				result += "{";
				result += "	var oOption;var length;var side;var i;var num;";
				result += "	num=document." + form + "." + depDropListID
						+ ".selectedIndex;";
				result += "	side=document." + form + "." + depDropListID
						+ ".options[num].value;"; //�õ�ѡ�еĲ��ŵ�id��

				for (int r = 0; r < depIDList.length; r++) {
					//ȡ�����еĲ��ŵ�id
					//�����еĲ���id����ѡ�е�id���Ƚϣ���������ȡ�����еĸò��ŵ��û�id
					result += "if (side==" + "\"" + depIDList[r][0] + "\""
							+ ")";
					result += "{";

					userIDList = this.getUserList(Long
							.parseLong(depIDList[r][0]));
					result += "		for(var x=document." + form + "."
							+ userDropListID + ".length-1;x>=0;x--){";
					result += "			document." + form + "." + userDropListID
							+ ".options[x] = null;";
					result += "		}";
					for (int u = 0; u < userIDList.length; u++) {
						result += "		oOption=document.createElement(" + "\""
								+ "OPTION" + "\"" + ");";
						result += "		oOption.text=" + "\"" + userIDList[u][1]
								+ "\"" + ";";
						result += "		oOption.value=" + "\"" + userIDList[u][0]
								+ "\"" + ";";
						result += "		document." + form + "." + userDropListID
								+ ".add(oOption);";

					}
					result += "	}";
				}
				result += "	}";
				result += "</script>";

				//new
				result += "<select name=" + depDropListID + " id="
						+ depDropListID
						+ "   LANGUAGE=javascript onchange='return  "
						+ depDropListID + "_onchange()'>";
				result += "\n" + "<option value=0><--��ѡ����--></option>";
				for (i = 0; i < depIDList.length; i++) {
					if (Long.parseLong(depIDList[i][0].toString()) == depID) {
						result += "\n" + "<option value=" + depIDList[i][0]
								+ " selected>" + depIDList[i][1] + "</option>";
					} else {
						result += "\n" + "<option value=" + depIDList[i][0]
								+ ">" + depIDList[i][1] + "</option>";
					}
				}
				result = result + "</select>";
				result += "<select name=" + userDropListID + " id="
						+ userDropListID + " >";
				result += "\n" + "<option value=0><--��ѡ���û�--></option>";
				if (uID > 0 && depID > 0) {
					userIDList = this.getUserList(depID);

					for (i = 0; i < userIDList.length; i++) {
						if (Long.parseLong(userIDList[i][0].toString()) == uID) {
							result += "\n" + "<option value="
									+ userIDList[i][0] + " selected >"
									+ userIDList[i][1] + "</option>";
						} else {
							result += "\n" + "<option value="
									+ userIDList[i][0] + "  >"
									+ userIDList[i][1] + "</option>";
						}
					}

				}

				else {
					userIDList = this.getUserList(Long
							.parseLong(depIDList[0][0]));
					for (i = 0; i < userIDList.length; i++) {
						result += "\n" + "<option value=" + userIDList[i][0]
								+ " >" + userIDList[i][1] + "</option>";
					}
				}
				result = result + "</select>";
			} catch (Exception e) {

			} finally {
				rs.close();
				dbo.close();
			}
		} catch (Exception e) {

		}
		return result;
	}

	/**
	 * �õ��û�auto_id,title���б�
	 * @param deep �ͺŵ����
	 * @return String[][]
	 */
	public String[][] getModelDeepList(long deep) {
		String[][] list = null;

		ResultSet rs = null;
		String sql = "";
		int count = 0;
		int i = 0;
		smart.css.database.DBOperator dbe = new smart.css.database.DBOperator();
		sql = "select auto_id,title from project_tree where deep=" + deep
				+ " order by sequence";
		try {
			//�ó�����ĳ���
			count = dbe.getCountValue(sql);
			list = new String[count][2];
			rs = dbe.executeQuery(sql);
			while (rs.next()) {
				list[i][0] = rs.getString("auto_id");
				list[i][1] = rs.getString("title");
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//ע�����еĹرձ�����finally��ִ��
			try {
				dbe.close(rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * �õ��û�auto_id,title���б�
	 * @param deep �ͺŵ����
	 * @return String[][]
	 */
	public String[][] getChildModelList(long parentID) {
		String[][] list = null;

		ResultSet rs = null;
		String sql = "";
		int count = 0;
		int i = 0;
		smart.css.database.DBOperator dbe = new smart.css.database.DBOperator();
		sql = "select auto_id,title from project_tree where parent_id="
				+ parentID + " order by sequence";
		try {
			//�ó�����ĳ���
			count = dbe.getCountValue(sql);
			list = new String[count][2];
			rs = dbe.executeQuery(sql);
			while (rs.next()) {
				list[i][0] = rs.getString("auto_id");
				list[i][1] = rs.getString("title");
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//ע�����еĹرձ�����finally��ִ��
			try {
				dbe.close(rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * �õ��ͺ���������
	 * @sql formָ����������form,thisDeepID���������name,nextDeepID�¸��������name
	 */
	public String getModelList(String form, String thisDeepID,
			String nextDeepID, long deep) {
		String result = "";
		ResultSet rs = null;
		int i = 0;
		int j = 0;
		String[][] thisDeep = this.getModelDeepList(deep); //ȡ�ò��ż�����id�б�
		//out.print(thisDeep.length);
		String[][] nextDeep = null; //�û�id�б�
		try {
			smart.css.database.DBOperator dbo = new smart.css.database.DBOperator();
			try {
				if (deep < 6) {
					result += "<script language=javascript>";
					result += "function " + thisDeepID + "_onchange()";
					result += "{";
					result += "	var oOption;var length;var side;var i;var num;";
					result += "	num=document." + form + "." + thisDeepID
							+ ".selectedIndex;";
					result += "	side=document." + form + "." + thisDeepID
							+ ".options[num].value;"; //�õ�ѡ�еĲ��ŵ�id��

					result += "if (side==" + "\"0\"" + "){ return; }";

					for (int r = 0; r < thisDeep.length; r++) {
						//ȡ�����еĲ��ŵ�id
						//�����еĲ���id����ѡ�е�id���Ƚϣ���������ȡ�����еĸò��ŵ��û�id
						result += "if (side==" + "\"" + thisDeep[r][0] + "\""
								+ ")";
						result += "{";

						nextDeep = this.getChildModelList(Long
								.parseLong(thisDeep[r][0]));
						result += "		for(var x=document." + form + "."
								+ nextDeepID + ".length-1;x>=0;x--){";
						result += "			document." + form + "." + nextDeepID
								+ ".options[x] = null;";
						result += "		}";

						result += "		oOption=document.createElement(" + "\""
								+ "OPTION" + "\"" + ");";
						result += "		oOption.text=" + "\"��ѡ��\"" + ";";
						result += "		oOption.value=" + "\"0\"" + ";";
						result += "		document." + form + "." + nextDeepID
								+ ".add(oOption);";

						for (int u = 0; u < nextDeep.length; u++) {
							result += "		oOption=document.createElement("
									+ "\"" + "OPTION" + "\"" + ");";
							result += "		oOption.text=" + "\"" + nextDeep[u][1]
									+ "\"" + ";";
							result += "		oOption.value=" + "\""
									+ nextDeep[u][0] + "\"" + ";";
							result += "		document." + form + "." + nextDeepID
									+ ".add(oOption);";
						}
						if (nextDeep.length == 0) {
							result += "return;";
						}
						result += "	}";
					}
					if (deep <= 4) {
						result += nextDeepID + "_onchange();";
					}
					result += "	}";
					result += "</script>";

					//new
					result += "<select name=" + thisDeepID + " id="
							+ thisDeepID
							+ "   LANGUAGE=javascript onchange='return  "
							+ thisDeepID + "_onchange()'>";
					result += "\n" + "<option value=0>��ѡ��</option>";
					if (deep == 1) {
						for (i = 0; i < thisDeep.length; i++) {
							result += "\n" + "<option value=" + thisDeep[i][0]
									+ ">" + thisDeep[i][1] + "</option>";
						}
					}
					result = result + "</select>";
				}
				if (deep == 6) {
					result += "<select name=" + thisDeepID + " id="
							+ thisDeepID + " >";
					result += "\n" + "<option value=0>��ѡ��</option>";

					result = result + "</select>";
				}
			} catch (Exception e) {

			} finally {
				dbo.close();
			}
		} catch (Exception e) {

		}
		return result;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * �õ������û�������
	 * @sql formָ����������form,depDropListID�����������name,userDropListID�û��������name
	 */
	public String getRelDepDrop(String form, String depDropListID,
			String userDropListID) {
		String result = "";
		ResultSet rs = null;
		int i = 0;
		int j = 0;
		String[][] depIDList = this.getDepList(); //ȡ�ò��ż�����id�б�
		//out.print(depIDList.length);
		String[][] userIDList = null; //�û�id�б�
		try {
			smart.css.database.DBOperator dbo = new smart.css.database.DBOperator();

			try {
				result += "<script language=javascript>";
				result += "\n" + "function " + depDropListID + "_onchange()";
				result += "\n" + "{";
				result += "\n"
						+ "	var oOption;var length;var side;var i;var num;";
				result += "\n" + "	num=document." + form + "." + depDropListID
						+ ".selectedIndex;";
				result += "\n" + "	side=document." + form + "." + depDropListID
						+ ".options[num].value;"; //�õ�ѡ�еĲ��ŵ�id��

				for (int r = 0; r < depIDList.length; r++) {
					//ȡ�����еĲ��ŵ�id
					//�����еĲ���id����ѡ�е�id���Ƚϣ���������ȡ�����еĸò��ŵ��û�id
					result += "\n" + "if (side==" + "\"" + depIDList[r][0]
							+ "\"" + ")";
					result += "\n" + "{";

					userIDList = this.getUserList(Long
							.parseLong(depIDList[r][0]));
					result += "\n" + "		for(var x=document." + form + "."
							+ userDropListID + ".length-1;x>=0;x--){";
					result += "\n" + "			document." + form + "."
							+ userDropListID + ".options[x] = null;";
					result += "\n" + "		}";
					for (int u = 0; u < userIDList.length; u++) {
						result += "\n" + "		oOption=document.createElement("
								+ "\"" + "OPTION" + "\"" + ");";
						result += "\n" + "		oOption.text=" + "\""
								+ userIDList[u][1] + "\"" + ";";
						result += "\n" + "		oOption.value=" + "\""
								+ userIDList[u][0] + "\"" + ";";
						result += "\n" + "		document." + form + "."
								+ userDropListID + ".add(oOption);";

					}
					result += "\n" + "	}";
				}
				result += "\n" + "	}";
				result += "\n" + "</script>";

				//new
				result += "<select name=" + depDropListID + " id="
						+ depDropListID
						+ "   LANGUAGE=javascript onchange='return  "
						+ depDropListID + "_onchange()'>";
				result += "\n" + "<option value=0><--��ѡ����--></option>";
				for (i = 0; i < depIDList.length; i++) {
					result += "\n" + "<option value=" + depIDList[i][0] + ">"
							+ depIDList[i][1] + "</option>";
				}
				result += "\n" + "</select>";

			} catch (Exception e) {

			} finally {
				dbo.close();
			}
		} catch (Exception e) {

		}
		return result;
	}

	/**
	 * �õ������û�������
	 * @sql formָ����������form,depDropListID�����������name,userDropListID�û��������name
	 */
	public String getRelUserDrop(String form, String depDropListID,
			String userDropListID) {
		String result = "";
		ResultSet rs = null;
		int i = 0;
		int j = 0;
		String[][] depIDList = this.getDepList(); //ȡ�ò��ż�����id�б�
		//out.print(depIDList.length);
		String[][] userIDList = null; //�û�id�б�
		try {
			smart.css.database.DBOperator dbo = new smart.css.database.DBOperator();

			try {

				result += "<select name=" + userDropListID + " id="
						+ userDropListID + " >";
				result += "\n" + "<option value=0><--��ѡ���û�--></option>";
				result += "\n" + "</select>";
			} catch (Exception e) {

			} finally {
				dbo.close();
			}
		} catch (Exception e) {

		}
		return result;
	}

	/**
	 * �õ������û�������
	 * @sql formָ����������form,depDropListID�����������name,userDropListID�û��������name
	 */
	public String getDefRelDepDrop(String form, String depDropListID,
			String userDropListID, long uID) {
		String result = "";
		ResultSet rs = null;
		int i = 0;
		int j = 0;
		long depID = this.getDepIDbyUID(uID);
		String[][] depIDList = this.getDepList(); //ȡ�ò��ż�����id�б�
		//out.print(depIDList.length);
		String[][] userIDList = null; //�û�id�б�
		try {
			smart.css.database.DBOperator dbo = new smart.css.database.DBOperator();

			try {
				result += "<script language=javascript>";
				result += "\n" + "function " + depDropListID + "_onchange()";
				result += "\n" + "{";
				result += "\n"
						+ "	var oOption;var length;var side;var i;var num;";
				result += "\n" + "	num=document." + form + "." + depDropListID
						+ ".selectedIndex;";
				result += "\n" + "	side=document." + form + "." + depDropListID
						+ ".options[num].value;"; //�õ�ѡ�еĲ��ŵ�id��

				for (int r = 0; r < depIDList.length; r++) {
					//ȡ�����еĲ��ŵ�id
					//�����еĲ���id����ѡ�е�id���Ƚϣ���������ȡ�����еĸò��ŵ��û�id
					result += "\n" + "if (side==" + "\"" + depIDList[r][0]
							+ "\"" + ")";
					result += "\n" + "{";

					userIDList = this.getUserList(Long
							.parseLong(depIDList[r][0]));
					result += "\n" + "		for(var x=document." + form + "."
							+ userDropListID + ".length-1;x>=0;x--){";
					result += "\n" + "			document." + form + "."
							+ userDropListID + ".options[x] = null;";
					result += "\n" + "		}";
					for (int u = 0; u < userIDList.length; u++) {
						result += "\n" + "		oOption=document.createElement("
								+ "\"" + "OPTION" + "\"" + ");";
						result += "\n" + "		oOption.text=" + "\""
								+ userIDList[u][1] + "\"" + ";";
						result += "\n" + "		oOption.value=" + "\""
								+ userIDList[u][0] + "\"" + ";";
						result += "\n" + "		document." + form + "."
								+ userDropListID + ".add(oOption);";

					}
					result += "\n" + "	}";
				}
				result += "\n" + "	}";
				result += "\n" + "</script>";

				//new
				result += "" + "<select name=" + depDropListID + " id="
						+ depDropListID
						+ "   LANGUAGE=javascript onchange='return  "
						+ depDropListID + "_onchange()'>";
				result += "\n" + "<option value=0><--��ѡ����--></option>";
				for (i = 0; i < depIDList.length; i++) {
					if (Long.parseLong(depIDList[i][0].toString()) == depID) {
						result += "\n" + "<option value=" + depIDList[i][0]
								+ " selected>" + depIDList[i][1] + "</option>";
					} else {
						result += "\n" + "<option value=" + depIDList[i][0]
								+ ">" + depIDList[i][1] + "</option>";
					}

				}
				result += "\n" + "</select>";

			} catch (Exception e) {

			} finally {
				rs.close();
				dbo.close();
			}
		} catch (Exception e) {

		}
		return result;
	}

	/**
	 * �õ������û�������
	 * @sql formָ����������form,depDropListID�����������name,userDropListID�û��������name
	 */
	public String getDefRelUserDrop(String form, String depDropListID,
			String userDropListID, long uID) {
		String result = "";
		ResultSet rs = null;
		int i = 0;
		int j = 0;
		String[][] depIDList = this.getDepList(); //ȡ�ò��ż�����id�б�
		//out.print(depIDList.length);
		String[][] userIDList = null; //�û�id�б�
		long depID = this.getDepIDbyUID(uID);
		try {
			smart.css.database.DBOperator dbo = new smart.css.database.DBOperator();

			try {

				result += "<select name=" + userDropListID + " id="
						+ userDropListID + " >";
				result += "\n" + "<option value=0><--��ѡ���û�--></option>";
				if (uID > 0 && depID > 0) {
					userIDList = this.getUserList(depID);

					for (i = 0; i < userIDList.length; i++) {
						if (Long.parseLong(userIDList[i][0].toString()) == uID) {
							result += "\n" + "<option value="
									+ userIDList[i][0] + " selected >"
									+ userIDList[i][1] + "</option>";
						} else {
							result += "\n" + "<option value="
									+ userIDList[i][0] + "  >"
									+ userIDList[i][1] + "</option>";
						}
					}

				}

				else {
					userIDList = this.getUserList(Long
							.parseLong(depIDList[0][0]));
					for (i = 0; i < userIDList.length; i++) {
						result += "\n" + "<option value=" + userIDList[i][0]
								+ " >" + userIDList[i][1] + "</option>";
					}
				}

				result += "\n" + "</select>";
			} catch (Exception e) {

			} finally {
				rs.close();
				dbo.close();
			}
		} catch (Exception e) {

		}
		return result;
	}

}
