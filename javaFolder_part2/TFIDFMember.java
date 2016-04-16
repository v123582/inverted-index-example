package hw2_part2;

public class TFIDFMember implements Comparable<TFIDFMember>{
	private String m_filename;
	private String m_query;
	private double m_TF;
	private double m_score;
	
	public void setFilename(String filename){
		m_filename = filename;
	}
	public void setQuery(String query){
		m_query = query;
	}
	public void setTF(double TF){
		m_TF = TF;
	}
	public void setScore(double score){
		m_score = score;
	}
	public String getFilename(){
		return m_filename;
	}
	public String getQuery(){
		return m_query;
	}
	public double getTF(){
		return m_TF;
	}
	public double getScore(){
		return m_score;
	}
	 public int compareTo(TFIDFMember input){
    	 if( m_score> input.getScore())
    		   return -1;
    	 else if( m_score < input.getScore())
    		   return 1;
    	 else
    		   return 0;
    		 
    }
}
