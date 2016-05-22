package pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import java.util.*;


public class DB_Connector {

	public Connection conn = null;
	
	public DB_Connector() {
	}

	public void connectDatabase() {
	    try {
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_shop", 
	                "root", 
	                "1234");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public int loginUser(String login, String password) {
	    int id;
		try {
	        Statement st = conn.createStatement();
	        ResultSet rs = st.executeQuery("SELECT * FROM client WHERE mail='" + login + "' AND password='" + password + "'");
	        if (rs.next() && (id = rs.getInt("id")) > 0) return id;
	        else return 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	        
	        return 0;
	    }
	}
	
    public List<Good> getGoodList(String name) {
        List<Good> goods = new ArrayList<Good>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(
            "SELECT assortment.*, company.name 'company', COUNT(*) 'count' FROM assortment INNER JOIN company ON assortment.company_id=company.id INNER JOIN good ON assortment.id=good.model_id GROUP BY good.model_id HAVING assortment.name='"+name+"'");
            
            while (rs.next()) {
            	goods.add(new Good(rs.getInt("id"), rs.getString("name"), rs.getString("model"), rs.getDouble("price"), rs.getDouble("discount"), rs.getDouble("rating"), rs.getString("company"), rs.getString("path"), rs.getInt("count")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return goods;
    }
    
    public List<String> getCompanyList() {
        List<String> companies = new ArrayList<String>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(
            "SELECT name FROM company");
            
            while (rs.next()) {
            	companies.add(new String(rs.getString("name")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return companies;
    }
    
    
    public List<Stores> getStoresList(int id) {
        List<Stores> stores = new ArrayList<Stores>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(
            "SELECT storage.*, COUNT(*) 'count', good.status FROM (SELECT good.* FROM good WHERE model_id='"+id+"') AS good INNER JOIN storage ON good.storage_id=storage.id GROUP BY storage.id HAVING good.status='free';");
            
            while (rs.next()) {
            	stores.add(new Stores(rs.getString("adress"), rs.getString("metro"), rs.getInt("count")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stores;
    }
    
    public List<Good> getFilteredGoodList(int price_low, int price_high, double rating_low, double rating_high, String name, String company) {
        List<Good> goods = new ArrayList<Good>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(
            "SELECT assortment.*, company.name 'company', COUNT(*) 'count' FROM assortment INNER JOIN company ON assortment.company_id=company.id INNER JOIN good ON assortment.id=good.model_id GROUP BY good.model_id HAVING company='"+company+"' and assortment.name='"+name+"' and assortment.price BETWEEN '"+price_low+"' and '"+price_high+"' and assortment.rating BETWEEN '"+rating_low+"' and '"+rating_high+"'");
            
            while (rs.next()) {
            	goods.add(new Good(rs.getInt("id"), rs.getString("name"), rs.getString("model"), rs.getDouble("price"), rs.getDouble("discount"), rs.getDouble("rating"), rs.getString("company"), rs.getString("path"), rs.getInt("count")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return goods;
    }
    
    public List<Good> getFilteredGoodList(int price_low, int price_high, double rating_low, double rating_high, String name) {
        List<Good> goods = new ArrayList<Good>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(
            "SELECT assortment.*, company.name 'company', COUNT(*) 'count' FROM assortment INNER JOIN company ON assortment.company_id=company.id INNER JOIN good ON assortment.id=good.model_id GROUP BY good.model_id HAVING assortment.name='"+name+"' and assortment.price BETWEEN '"+price_low+"' and '"+price_high+"' and assortment.rating BETWEEN '"+rating_low+"' and '"+rating_high+"'");
            
            while (rs.next()) {
            	goods.add(new Good(rs.getInt("id"), rs.getString("name"), rs.getString("model"), rs.getDouble("price"), rs.getDouble("discount"), rs.getDouble("rating"), rs.getString("company"), rs.getString("path"), rs.getInt("count")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return goods;
    }
    
    public Good getGood(int id) {
        Good good = new Good();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(
            "SELECT assortment.*, company.name 'company', COUNT(*) 'count' FROM assortment INNER JOIN company ON assortment.company_id=company.id INNER JOIN good ON assortment.id=good.model_id GROUP BY good.model_id HAVING assortment.id='"+id+"'");
            
            while (rs.next()) {
            	good.set_all(rs.getInt("id"), rs.getString("name"), rs.getString("model"), rs.getDouble("price"), rs.getDouble("discount"), rs.getDouble("rating"), rs.getString("company"), rs.getString("path"), rs.getInt("count"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return good;
    }
    
    
    public List<Reports> getReportList(int id) {
        List<Reports> reports = new ArrayList<Reports>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(
            "SELECT * FROM report WHERE id_model='"+id+"'");
            
            while (rs.next()) {
            	reports.add(new Reports(rs.getString("about"), rs.getDate("date"), rs.getString("author")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reports;
    }
    
    public boolean makeVote(int id_pers, double mark, int model_id) {
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(
            "SELECT * FROM marks WHERE id_client='"+id_pers+"' and id_model='"+model_id+"'");
	        if (rs.next() && rs.getInt("id") > 0) 
	        	return false;
	        else
	        {
	        	st.executeUpdate("INSERT INTO marks VALUES (DEFAULT,'"+ model_id  + "', '" + id_pers + "', '" + mark + "')");
	        	st.executeUpdate("UPDATE assortment SET votes=votes + 1 WHERE id='"+ model_id+ "'");
	        	st.executeUpdate("UPDATE assortment SET rating=(rating+" + mark+ ")/votes WHERE id='"+ model_id+ "'");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    
    public boolean ConfirmOrderAuto(int pers_id, int model_id, int count, String status, String location, String card) {
        try {
            Statement st = conn.createStatement();
            int [] arr = new int[count];
            int i = 0;
            Date date = new Date();
            String ins_date = new SimpleDateFormat("yyyy-MM-dd").format(date);
            
            ResultSet rs = st.executeQuery(
            "SELECT * FROM good WHERE model_id='"+model_id+"' and status='free'");
	        
            while(rs.next() && (i < count)){
	        	arr[i] = rs.getInt("id");
	        	i++;
	        }
            
            for (int j = 0; j < count; j++){
            	st.executeUpdate("UPDATE good SET status ='"+status+"' WHERE id='"+arr[j]+"'");
            }
            
            for (int j = 0; j < count; j++){
            	st.executeUpdate("INSERT INTO orders VALUES (DEFAULT,'"+ pers_id  + "', '" + arr[j] + "', '" + ins_date + "','"+location+"','"+card+"')");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    
    
    public boolean makeReport(int id_pers, String about, int model_id, String author) {
        try {
            Statement st = conn.createStatement();
            Date cur_date = new Date();
            String ins_date = new SimpleDateFormat("yyyy-MM-dd").format(cur_date);
	        st.executeUpdate("INSERT INTO report VALUES (DEFAULT,'"+ model_id  + "', '" + id_pers + "', '" + about + "','"+ins_date+"','"+author+"')");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
	
    public boolean RegisterNewUser(Person pers)
    {
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(
            		"INSERT INTO client VALUES (DEFAULT,'"
            	            + pers.get_name() + "', '" + pers.get_second_name() + "', '" + pers.get_surname() + "', '" + pers.get_sex() + "', '" + pers.get_phone() +"', '"+ pers.get_mail() + "', '" + pers.get_password() + "')");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    
    
    public int SetField(int index, String name)
    {
        try {
            Statement st = conn.createStatement();
            int res = st.executeUpdate(
            		"UPDATE assortment SET path = 'images/"+ name +((int)(Math.random() * 100) % 4)+".jpg' WHERE path='x' and name='"+name+"' LIMIT 1");
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    
	
	
    public String printHead(String title) {
        return 
                "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "        <title>" + title + "</title>\n" +
                "        <link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">\n";
                
    }
}
