import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;

public class Page implements Serializable {
	
	int max ;
	public Vector<Hashtable<String, Object>> pageList;

	public Page() {
		pageList=new Vector<Hashtable<String, Object>>();
		
		File configFile = new File("config/DBApp.config");
		try {
		    FileReader reader = new FileReader(configFile);
		    Properties props = new Properties();
		    props.load(reader);
		    max = Integer.parseInt(props.getProperty("MaximumRowsCountinPage"));
		  
		    reader.close();
		} catch (Exception ex) {
			  
		    // I/O error
		}
	}
	
	
	
	public boolean isFull() {
		//System.out.print(pageList.size()+" "+max);
		if (this.pageList.size()>=max) {
			System.out.println("True");
			return true;
			}
		else {
			System.out.println("False");
			return false;
		}
		
	}	
//	public static void l(){
//		System.out.print("l");}
//	public void  addTuple(Hashtable<String,Object> x,String clusterKey) {
//		boolean f=true;
//		
//		int n=pageList.size();
//		if(pageList.size()==0)
//			pageList.add(x);
//		else {
//			
//			for(int i=0;i<n&&f==true;i++){ 
//				//System.out.println((int) x.get(clusterKey)+"="+((int)(pageList.get(i).get(clusterKey))));
//				if((int) x.get(clusterKey)<=((int)(pageList.get(i).get(clusterKey)))) {
//					pageList.add(i,x);
//					f=false;
//					
//					
//					
//				}
//			}
//			
//			if(f==true) {
//				pageList.add(x);
//			
//		}}
//	//System.out.print("ffff"+pageList.get(2));
//		
//		}
		
	
	public void addTuple(Hashtable<String, Object> x, String strClusteringKey) {
		String y = x.get(strClusteringKey).getClass().getName();

		for (int i = 0; i < this.pageList.size(); i++) {
//			System.out.println(this.pageList.get(i).get(strClusteringKey)
//					+ "   " + x.get(strClusteringKey));
			if (y.equals("java.lang.Integer")) {
//				System.out.println(this.pageList.get(i).get(strClusteringKey)
//						.getClass().getName());
				if (((Integer) this.pageList.get(i).get(strClusteringKey))
						.compareTo((Integer) x.get(strClusteringKey)) == 1) {
					this.pageList.add(i, x);
					return;
				}
			} else if (y.equals("java.lang.Double")) {
				if (((Double) this.pageList.get(i).get(strClusteringKey))
						.compareTo((Double) x.get(strClusteringKey)) == 1) {

					this.pageList.add(i, x);
					return;
				}
			} else if (y.equals("java.util.Date")) {
				if (((Date) this.pageList.get(i).get(strClusteringKey))
						.compareTo((Date) x.get(strClusteringKey)) == 1) {
					this.pageList.add(i, x);
					return;
				}
			} else if (y.equals("java.lang.String")) {
				System.out.println("p="+((String) this.pageList.get(i).get(strClusteringKey)).compareTo((String) x.get(strClusteringKey)));
				if (((String) this.pageList.get(i).get(strClusteringKey))
						.compareTo((String) x.get(strClusteringKey)) >= 0) {
					this.pageList.add(i, x);
					return;
				}
			} else if (y.equals("java.lang.Boolean")) {
				if (((Boolean) this.pageList.get(i).get(strClusteringKey))
						.compareTo((Boolean) x.get(strClusteringKey)) == 1) {
					this.pageList.add(i, x);
					return;
				}
			}
		}

		this.pageList.add(x);

	}
		
public boolean belongs(Hashtable x,String clusterKey) {
	if((int) x.get(clusterKey)<=((int)(pageList.get(0).get(clusterKey))))
		return true;
	else return false;
	
}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
;
	}

}
