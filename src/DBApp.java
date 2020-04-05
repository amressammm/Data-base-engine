import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;



//import UnFound.Page;



public class DBApp  {

	

	

public static boolean checkDuplicates(String tableName) throws EOFException {
	try {
		boolean pp=false;
	FileInputStream x=new FileInputStream("classes/DBApp.class");
	ObjectInputStream y=new ObjectInputStream(x);
	 ArrayList<String[]> z= (ArrayList<String[]>) y.readObject();
	 for (int i = 0; i < z.size(); i++) {
			if (z.get(i)[0].equals(tableName)) 
				pp=true;
			else pp=false;
			}
	
	return(pp);
	
	
	
	 }catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		
		// TODO Auto-generated catch block
		//e.printStackTrace();
		}
	catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return false;
}
	
public static void writeTable(String tableName,String clusterKey) {
	FileInputStream x;
	ObjectInputStream y;
	ArrayList<String[]>z;
	try {
	x=new FileInputStream("classes/DBApp.class");
	y=new ObjectInputStream(x);
	z=(ArrayList<String[]>)y.readObject();
	}catch(Exception e) {
		z=new ArrayList<String[]>();
		
	}
	String[] s= {tableName,"0",clusterKey};
	z.add(s);
	try {
	FileOutputStream a = new FileOutputStream("classes/DBApp.class");
	ObjectOutputStream b = new ObjectOutputStream(a);
	b.writeObject(z);
	a.close();
	b.close();
	}catch(Exception e) {
		
	}

}


private static void WriteCsv(String strTableName, String strClusteringKeyColumn,
		Hashtable<String, String> htblColNameType) throws Exception {
	// Table Name, Column Name, Column Type, ClusteringKey.
	FileWriter br = new FileWriter("data/metadata.csv", true);
	Enumeration<String> e = htblColNameType.keys();
	while (e.hasMoreElements()) {
		String key = (String) e.nextElement();
		br.append(strTableName + ",");
		br.append(key + ",");
  
		
		
		if (!(htblColNameType.get(key).equals("java.lang.Integer")
				|| htblColNameType.get(key).equals("java.lang.String")
				|| htblColNameType.get(key).equals("java.lang.Double")
				|| htblColNameType.get(key).equals("java.lang.Boolean") 
				|| htblColNameType.get(key).equals("java.util.Date") || htblColNameType
				.get(key).equals("java.awt.Polygon"))) {
			br.flush();
			br.close();
			throw new Exception("Wrong Formate");
		}
		br.append(htblColNameType.get(key) + ",");
           //System.out.println(key);
		if (key.equals(strClusteringKeyColumn))
			br.append("True,");
		else
			br.append("False,");
		br.append("False,");

		br.append('\n');

	}
	br.append(strTableName
			+ ",TouchDate,"
			+ "java.util.Date"
			+ ","
			+ ("TouchDate".equals(strClusteringKeyColumn) ? "True"
					: "False") + ",False"+'\n');
	br.flush();
	br.close();
}

public static int numberofPages (String name) throws IOException, ClassNotFoundException {
	FileInputStream k=new FileInputStream("classes/DBApp.class");
	ObjectInputStream l=new ObjectInputStream(k);
	ArrayList<String[]>p=(ArrayList<String[]>)l.readObject();
	
	for (int i = 0; i < p.size(); i++) {
		if (p.get(i)[0].equals(name)) {
			k.close();
			l.close();
			//System.out.print(p.get(i)[1]);
			//System.out.println("k");
			return Integer.parseInt(p.get(i)[1]);
		
}}
	k.close();
	l.close();
return -1;	
}
public static  String findClusterkey(String name) throws IOException, ClassNotFoundException {
	
	FileInputStream k=new FileInputStream("classes/DBApp.class");
	ObjectInputStream l=new ObjectInputStream(k);
	ArrayList<String[]>p=(ArrayList<String[]>)l.readObject();
	for (int i = 0; i < p.size(); i++) {
		if (p.get(i)[0].equals(name)) {
			k.close();
			l.close();
			return (p.get(i)[2]);
		
}}return null;
}

public static void writePage(String tableName, String id, Page p) throws IOException {
	FileOutputStream x = new FileOutputStream("data/"
			+ tableName + id + ".class");
	ObjectOutputStream y = new ObjectOutputStream(x);
	y.writeObject(p);
	x.close();
	y.close();
}

public static void editNumberofPage(String Name,String n) throws IOException, ClassNotFoundException {
	
	FileInputStream x = new FileInputStream("classes/DBApp.class");
	ObjectInputStream y = new ObjectInputStream(x);
	
	ArrayList<String[]> z = (ArrayList<String[]>) y.readObject();
	for (int i = 0; i < z.size(); i++) {
		if (z.get(i)[0].equals(Name))
			//System.out.print(z.get(i)[1]);
			z.get(i)[1]=n;
			//System.out.print(z.get(i)[1]);
	}
	
	FileOutputStream o=new FileOutputStream("classes/DBApp.class");
	ObjectOutputStream p=new ObjectOutputStream(o);
	p.writeObject(z);
	
	y.close();
	x.close();
	y.close();
	
}

public static Page getPage(String tableName, String id) throws Exception {
	FileInputStream x = new FileInputStream("data/" + tableName
			+ id + ".class");
	ObjectInputStream y = new ObjectInputStream(x);
	Page z = (Page) y.readObject();
	x.close();
	y.close();
	return z;
}


public static tablePages gettablePages(String tableName) throws ClassNotFoundException, IOException {//000000000000
	FileInputStream x = new FileInputStream("data/" + tableName + ".class");
	ObjectInputStream y = new ObjectInputStream(x);
	tablePages z = (tablePages) y.readObject();
	x.close();
	y.close();
	return z;
	
}

 public static void updatetablePages(Page p,int i,String tableName) throws ClassNotFoundException, IOException {//000000000000
	tablePages t= gettablePages(tableName);
	t.updatetable(p, i, tableName);
	
	 
	 
 }
 public static void writetablePages(tablePages t,String tableName) throws IOException {//0000000000000000000000
	
	 FileOutputStream x = new FileOutputStream("data/"
				+ tableName + ".class");
		ObjectOutputStream y = new ObjectOutputStream(x);
		y.writeObject(t);
		x.close();
		y.close();
		
 }
 
 public static Hashtable<String, Object> passByValue(Hashtable<String, Object> obj) {
		return obj;
	}
 
 
 
 public static void deletePage(String tableName , int index ){
	 File file = new File("data/"+tableName+index+".class"); 
     
        if(file.delete()) 
        { 
            System.out.println("File deleted successfully"); 
        } 
        else
        { 
            System.out.println("Failed to delete the file"); 
        } 
}
 
 
 public static void shift(tablePages x,String tableName) throws ClassNotFoundException, IOException {
	 
	 for(int i=0;i<x.Pages.size();i++) {
		 
		 if(((Page)x.Pages.get(i)).pageList.size()==0)
		 { x.Pages.remove(i);
		 deletePage(tableName,i);
		 
		 System.out.println(i);
		 }
		
		 
	 }
	 	 x.writetablePages(tableName);
	 editNumberofPage(tableName,x.Pages.size()+"");
	 System.out.println(numberofPages(tableName));

	 for(int i=0;i<x.Pages.size()-1;i++) {
		 
		
			Page y=(Page) x.Pages.get(i);
			
			while(!(y.isFull())){
				
				Page f=(Page) x.Pages.get(i+1);
				y.pageList.add(f.pageList.remove(0));
				
				x.updatetable(f, i+1, tableName);
				
				if(f.pageList.size()==0) {
					x.Pages.remove(i+1);
				    x.writetablePages(tableName);
				    editNumberofPage(tableName,x.Pages.size()+"");
				    deletePage(tableName,i);
				    System.out.println("g2");
					break;
				
				}
					
			 
			 
		 }
			x.updatetable(y, i, tableName);
		 
	 }
	 
	 
 }
 
 
 public static void inserttuple(tablePages x ,Hashtable y,String tabelName) throws ClassNotFoundException, IOException {
	 
	 String z=findClusterkey(tabelName);
//	 int k=(int) y.get(z);
	
	
	 
	 for(int i=0;i<x.Pages.size();i++) {
		 int l=((Page)x.Pages.get(i)).pageList.size();
		 Page f=((Page)x.Pages.get(i));
		 
		 System.out.println("sos "+i);
		 if((i+1)==x.Pages.size()) {
			 f.addTuple(y, z);
			 updatetablePages(f,i,tabelName);
			 writePage(tabelName,i+"",f);
			
			 return;
		 }
		
		// System.out.println(belongs(k,(Page)x.Pages.get(i),z));
		 //belongs(k,f,z)
		 if(belongs(f,z,f.pageList.get(i).get(z))) {
			System.out.println("e");
			
			
			 if(f.isFull()) {
			 Hashtable<String, Object> d=f.pageList.get(l-1);
			
			
			
			 
			 
			 
			 
			 
			 
			 Page b=((Page)x.Pages.get(i+1));
			 System.out.print("ll"+i);
			 if(!((Page)x.Pages.get(i+1)).isFull()) {
				
				 System.out.println("n");
				 f.pageList.remove(l-1);
				
				 f.addTuple(y,z);
				 updatetablePages(f,i,tabelName);
				 writePage(tabelName,i+"",f);
			
				b.addTuple(d,z);
				
				updatetablePages(b,i+1,tabelName);
				 writePage(tabelName,(i+1)+"",b);
				 return;
				 }
			 else {
				int a;
				int j;
				 System.out.print(((Page)(x.Pages.get(i+1))).pageList.size());
				 
				 for(j=i;j<x.Pages.size()&&((((Page)x.Pages.get(j)).isFull()));j++) {
					 System.out.println("oooooo");
					 System.out.println("j="+j);
					 Page g=((Page)x.Pages.get(j));
					 Hashtable<String, Object> r=g.pageList.remove(g.pageList.size()-1);
					 System.out.println("gggg  "+r.get("ID"));
					 System.out.println("hhh "+y.get("ID"));
					
					 g.addTuple(y,z);
					 updatetablePages(g,j,tabelName);
					 writePage(tabelName,j+"",f);
					 
					 y=passByValue(r); 
					 
//					  g=((Page)x.Pages.get(j+1));
//					  r=g.pageList.remove(g.pageList.size()-1);
					  
					 System.out.println("yaaa"+ r.get("ID"));
				
					 
					System.out.println("yaaa"+ y.get("ID"));
					
					 
				 }
				 a=j;
				 Page g=((Page)x.Pages.get(a));
				 System.out.println("yarab "+d.get("ID"));
				 g.addTuple(y,z);
				 System.out.println("pp"+a);
				 updatetablePages(g,a,tabelName);
				 writePage(tabelName,a+"",f);
				 return;
				 
			 }
			 
			 
			
			 }
			 
			System.out.print("kk");
			 f.addTuple(y,z);
			 updatetablePages(f,i,tabelName);
			 writePage(tabelName,i+"",f);
			 return;
			 
	 }
		}
	 
	 
	
	 
 }
 
 public static boolean belongs(Page page, String strClusteringKey, Object value) {
		if (!page.isFull())
			return true ;
		String y = page.pageList.get(0).get(strClusteringKey).getClass()
				.getName();
		// System.out.println(this.pageList.get(i).get(strClusteringKey)
		// + "   " + x.get(strClusteringKey));
		if (y.equals("java.lang.Integer")) {
			// System.out.println(this.pageList.get(i).get(strClusteringKey)
			// .getClass().getName());
			if (((Integer) page.pageList.get(0).get(strClusteringKey))
					.compareTo((Integer) value) != 1
					&& ((Integer) page.pageList.get(page.pageList.size() - 1)
							.get(strClusteringKey)).compareTo((Integer) value) != -1) {
				return true;
			}
			else if (((Integer) page.pageList.get(0).get(strClusteringKey))
					.compareTo((Integer) value) == 1)
				return false ; 
			else return false ; 
		} else if (y.equals("java.lang.Double")) {
			if (((Double) page.pageList.get(0).get(strClusteringKey))
					.compareTo((Double) value) != 1
					&& ((Double) page.pageList.get(page.pageList.size() - 1)
							.get(strClusteringKey)).compareTo((Double) value) != -1) {
				return true;
			}
			else if (((Double) page.pageList.get(0).get(strClusteringKey))
					.compareTo((Double) value) == 1)
				return false ; 
			else return false ; 
		} else if (y.equals("java.util.Date")) {
			if (((Date) page.pageList.get(0).get(strClusteringKey)).compareTo((Date) value) != 1&& ((Date) page.pageList.get(page.pageList.size() - 1).get(strClusteringKey)).compareTo((Date) value) != -1) {
				return true;

			}
			else if (((Date) page.pageList.get(0).get(strClusteringKey))
					.compareTo((Date) value) == 1)
				return false ; 
			else return false ; 
		} else if (y.equals("java.lang.String")) {
			if (((String) page.pageList.get(0).get(strClusteringKey))
					.compareTo((String) value) != 1
					&& ((String) page.pageList.get(page.pageList.size() - 1)
							.get(strClusteringKey)).compareTo((String) value) != -1) {
				return true;

			}
			else if (((String) page.pageList.get(0).get(strClusteringKey))
					.compareTo((String) value) == 1)
				return false ; 
			else return false ; 
		} else if (y.equals("java.lang.Boolean")) {
			if (((Boolean) page.pageList.get(0).get(strClusteringKey))
					.compareTo((Boolean) value) != 1
					&& ((Boolean) page.pageList.get(page.pageList.size() - 1)
							.get(strClusteringKey)).compareTo((Boolean) value) != -1) {
				return true;
			}
			else if (((Boolean) page.pageList.get(0).get(strClusteringKey))
					.compareTo((Boolean) value) == 1)
				return false ; 
			else return false ; 
		}

		return true;

	}
 
 
// public static boolean belongs(int k,Page p,String clusterKey) {
//	
//	 if(p.pageList.size()==0)
//		 return true;
//	 else {
////		 System.out.println("1  "+!(p.isFull()));
////		 System.out.println("2 "+(x.Pages.size()>(i+1)));
////		 System.out.println("3 "+(((Page)(x.Pages.get(i+1))).pageList.size()!=0));
////		 System.out.println("4 "+(k<=(int)(((Page)(x.Pages.get(i+1))).pageList.get(0).get(clusterKey))));
////		 
////		 
////		 if(!(p.isFull())&&(x.Pages.size()>(i+1)&&((Page)(x.Pages.get(i+1))).pageList.size()!=0&&k<=(int)(((Page)(x.Pages.get(i+1))).pageList.get(0).get(clusterKey)))){
////			
////			 return true;
////		 }
//		 
//		// System.out.println(k+"  "+(int)p.pageList.get(p.pageList.size()-1).get(clusterKey));
//	 if((k>=(int)p.pageList.get(0).get(clusterKey)&&(k<=(int)p.pageList.get(p.pageList.size()-1).get(clusterKey)))||k<(int)p.pageList.get(0).get(clusterKey)) {
//			
//		 return true;}
//		 //System.out.print("hh");
//	 
//		 return false;
//		 
// }}
 public static boolean fullRecord(String strTableName,Hashtable<String, Object> htblColNameValue) throws IOException {
	 Enumeration<String> colName = htblColNameValue.keys();
		int i=0;
			while (colName.hasMoreElements()) {
				colName.nextElement();
				
				i++;
				
			}
				String line = "";
				String cvsSplitBy = ",";
				BufferedReader br = new BufferedReader(new FileReader("data/metadata.csv"));
				boolean f = false;
				String[] r = null;
			int j=0;
				while ((line = br.readLine()) != null) {
					// use comma as separator
					 r = line.split(cvsSplitBy);
					
					j++;
					//System.out.println(	htblColNameValue.get(el).getClass().getName()+""+el);
				}
				j--;
				
				if (i==j)
				return true;
				
				else 
					return false;
				
		}
	 
 

 public static Boolean validateTable(String strTableName,Hashtable<String, Object> htblColNameValue) {
		Enumeration<String> colName = htblColNameValue.keys();
		try {
			while (colName.hasMoreElements()) {
				String line = "";
				String cvsSplitBy = ",";
				BufferedReader br = new BufferedReader(new FileReader("data/metadata.csv"));
				String el = colName.nextElement();
				boolean f = false;
				while ((line = br.readLine()) != null) {
					// use comma as separator
					String[] r = line.split(cvsSplitBy);
					
					if ((r[0].equals(strTableName) && r[1].equals(el) && (((htblColNameValue
							.get(el)).getClass()).getName()).equals(r[2])))
						f = true;
					//System.out.println(	htblColNameValue.get(el).getClass().getName()+""+el);
				}
				if (!f)
					return false;
			}
			return true;
		} catch (Exception e) {
			// //////////////////////////
			return false;
		}
	}

public  void createTable(String strTableName, String strClusteringKeyColumn,Hashtable <String,String> htblColNameType )
			 throws DBAppException, IOException {
	
	if(checkDuplicates(strTableName))
		return ;
	
	else {
		writeTable(strTableName,strClusteringKeyColumn);
		tablePages x=new tablePages(strTableName); 
		writetablePages(x,strTableName);
		//000000000000000000000000000000000000
		try {
			WriteCsv(strTableName,strClusteringKeyColumn,htblColNameType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
}

public  void  insertIntoTable(String strTableName, Hashtable<String,Object> htblColNameValue) throws Exception {
	
	
	if(validateTable(strTableName,htblColNameValue)== false||fullRecord(strTableName,htblColNameValue)==false)
	{
		System.out.print("wrong input");
		return;
	}
	else {  
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		   LocalDateTime now = LocalDateTime.now();  
		htblColNameValue.put("TouchDate",dtf.format(now));
		tablePages g=gettablePages(strTableName);
		shift(g,strTableName);
	int x=numberofPages(strTableName);
	
	
	 System.out.println("x="+x);
	if(x==0) {
		System.out.println("f2"+x);
		Page P=new Page();
	P.addTuple(htblColNameValue,findClusterkey(strTableName));	
		writePage(strTableName,x+"",P);
		editNumberofPage(strTableName,(x+1)   +"");
		
		tablePages t=gettablePages(strTableName);//00000000000000000
		t.addpages(P, strTableName);//0000000000000000
		
	}
	
	else {
		
		System.out.println("f1"+x);
		Page p1=getPage(strTableName,(x-1)+"");
		 System.out.println("size"+p1.pageList.size());
		 System.out.print("1st element ="+p1.pageList.get(0).get("id"));
		if((p1.isFull())==false) {
		
			System.out.println("h");
////	///		p1.addTuple(htblColNameValue,findClusterkey(strTableName));
			inserttuple(g,htblColNameValue,strTableName);//000000000000000000
			
		//writePage(strTableName,(x-1) +"",p1);
		
		//tablePages t=gettablePages(strTableName);//0000000000000000000
		//updatetablePages(p1,(x-1),strTableName);//00000000000000000000
		
		
		}
		else {
			
			
			Page p2 =new Page();
			g.addpages(p2, strTableName);//000000000000000000000
/////			p2.addTuple(htblColNameValue,findClusterkey(strTableName));
			//System.out.println(x);
			System.out.print("lol");
			writePage(strTableName,x +"",p2);
			editNumberofPage(strTableName, (x+1) +"");
			inserttuple(g,htblColNameValue,strTableName);
			//tablePages t=gettablePages(strTableName);//00000000000
			//t.addpages(p2, strTableName);//0000000000000000
			
		}
		
		
	}}
	
}
public void updateTable(String strTableName,String strClusteringKey,Hashtable<String,Object> htblColNameValue )throws Exception   {
	if(validateTable(strTableName,htblColNameValue)== false)
	{
		System.out.print("wrong input");
		return;
	}
	else {  
	boolean f=false;
	int n =numberofPages(strTableName);
	String y=findClusterkey(strTableName);
	
	for (int i = 0; i < n; i++) {
		
		Page p = getPage(strTableName, i + "");
		for(int j=0;j<p.pageList.size();j++) {
			
			if( strClusteringKey.equals(p.pageList.get(j).get(y).toString())) {
				f=true;
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
				   LocalDateTime now = LocalDateTime.now();  
				   
				p.pageList.get(i).replace("TouchDate",dtf.format(now));
				//Hashtable<String, Object> g=p.pageList.get(j);
	            Enumeration<String> keyValues = htblColNameValue.keys();
				
	            while (keyValues.hasMoreElements()) {
	            	
					String k = (String) keyValues.nextElement();
					p.pageList.get(j).replace(k,  htblColNameValue.get(k));
					//.pageList.get(j).put(k, htblColNameValue.get(k));
				}
				
			}
				
			writePage(strTableName, i + "", p);
			updatetablePages(p,i,strTableName);//0000000000000000000000
	}
	}
	if(!f)
		System.out.print("row not found");
}}

public  void deleteFromTable(String strTableName, Hashtable<String,Object> htblColNameValue) throws Exception {
	int n =numberofPages(strTableName);
	String y=findClusterkey(strTableName);
	
	
//	for (int i = 0; i < n; i++) {
//		Page p = getPage(strTableName, i + "");
//		
//		for(int j=0;j<p.pageList.size();j++) {
//			Enumeration<String> r=htblColNameValue.keys();
//			//System.out.print(p.pageList.size());
//		while(r.hasMoreElements()) {
//			if(p.pageList.size()==0)
//				break;
//			String v=r.nextElement();
//			
//			if((htblColNameValue.get(v)).equals(p.pageList.get(j).get(v))) {
//		
//				p.pageList.remove(j);
//				
//				j--;
//				
//			}
//		}}
//		writePage(strTableName, i + "", p);
//	}
	
	tablePages x=gettablePages(strTableName);
	for(int i=0;i<x.Pages.size();i++) {
		Page z=(Page) x.Pages.get(i);
	
		
		for(int j=0;j<z.pageList.size();j++) {
			Enumeration<String> r=htblColNameValue.keys();
			System.out.println(z.pageList.size());
				
		while(r.hasMoreElements()) {
			if(z.pageList.size()==0)
				break;
			
			if(j<0)
				j++;
			String v=r.nextElement();
			
			if((htblColNameValue.get(v)).equals(z.pageList.get(j).get(v))) {
		System.out.println("f");
				z.pageList.remove(j);
				j--;
				
				System.out.println("j="+j);
				
//				if(i<(x.Pages.size()-1)) { //emmptyyy ????????
//					
//					Page s=(Page)x.Pages.get(i+1);
//					Hashtable<String, Object> t=s.pageList.remove(0);
//					z.addTuple(t,y);
//					
//					if(s.pageList.size()==0) {
//						x.Pages.remove(i+1);
//						 x.writetablePages(strTableName);
//						editNumberofPage(strTableName,x.Pages.size()+"");
//					
//					}else {
//						
//					 updatetablePages(s,i+1,strTableName);
//					
//					 
//					 writePage(strTableName,i+1+"",s);
//					
//					}
//					
//				}
				updatetablePages(z,i,strTableName);
				 writePage(strTableName,i+"",z);
				 break;
		}
		
		}
		
	
		
	}}
	
	
	
	
	
}

	
	
	
	public static void main(String[]args) throws Exception   {
		
//		Hashtable<String,String> a =new Hashtable<String, String>();
//		a.put("ab","java.lang.Integer");
//		a.put("c","java.lang.Integer");
//		a.put("d","java.lang.Double");
//		try {
//			createTable("abzo", "d", a);
//		} catch (DBAppException e) {System.out.print("lol");
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Hashtable<String,String> a =new Hashtable<String, String>();
//		a.put("name","java.long.String");
//		createTable("amr","Id",a);
////		
//		editNumberofPage("amr","0");
//////	
    Hashtable x=new Hashtable<String,String>();
		x.put("age","java.lang.Integer");
		x.put("id","java.lang.Double");
		//x.put("ID","java.lang.Integer");
		//createTable("inst","id",x);
////////	
		
// editNumberofPage("student","0");
		Hashtable y =new Hashtable<String,String>();
	
		//y.put("id",new Double(2));
		//y.put("ID",new Integer(1));
		//y.put("age","0");
		
     // insertIntoTable("inst",y);
		//updateTable("student","2.0",y);
//		System.out.print(numberofPages("student"));
   
		//deleteFromTable("student",y);
//	
//	try {
//		FileInputStream k=new FileInputStream("classes/DBApp.class");
//		ObjectInputStream l=new ObjectInputStream(k);
//		ArrayList j= (ArrayList)l.readObject();///////////////////////////////////////
//		String[] f=(String[]) j.get(0);
//		System.out.print((f[0]));
//	}catch (FileNotFoundException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();}
//////		
		
		//System.out.println(numberofPages("Student"));
//////		
		try {
			
			FileInputStream k=new FileInputStream("data/Student.class");
			ObjectInputStream l=new ObjectInputStream(k);
			tablePages j=(tablePages)l.readObject();///////////////////////////////////////
			ArrayList f=j.Pages;
			Page g=(Page) f.get(1);
			System.out.println(g.pageList.get(0).get("name"));
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		
//try {
//			
//			FileInputStream k=new FileInputStream("classes/Student1.class");
//			ObjectInputStream l=new ObjectInputStream(k);
//			Page j=(Page)l.readObject();///////////////////////////////////////
//			Vector<Hashtable<String, Object>> f=j.pageList;
//			//Page g=(Page) f.get(0);
//			System.out.print(f.get(0).get("id"));
//		}catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();}
////		
////		
//} catch (ClassNotFoundException e) {
//
	// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//		
//		Hashtable x=new Hashtable<String,String>();
//		x.put("Id","java.lang.Integer");
//		//x.put("Name", "String");
//		//x.put("age", "int");
//		
//		try {
//			WriteCsv("Stusen","Id",x);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
//		Hashtable<String,String> a =new Hashtable<String, String>();
//		a.put("ab","java.lang.Integer");
//		a.put("c","java.lang.Integer");
//		a.put("d","java.lang.Double");
//		try {
//			createTable("abkk", "d", a);
//		} catch (DBAppException e) {System.out.print("lol");
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//editNumberofPage("amr","0");
//		
//	Hashtable x=new Hashtable<String,int[]>();
//		//x.put("int",);
//		x.put("Name", "Amr");
//		
//  	insertIntoTable("amr",x);
//		
//		x.replace("Name","amr");
//		System.out.print(x.get("Name"));
//		

}}
