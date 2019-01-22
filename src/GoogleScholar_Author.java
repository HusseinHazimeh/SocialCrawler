import java.util.ArrayList;


/**
 * @author hussein.hazimeh 02.March.2018
 *
 */
public class GoogleScholar_Author {
	
	
	private String author_name;
	private String affiliation;
	private ArrayList<String> research_fields;
	private int n_of_citations;
	private String photo_url;
	private String image_url;
	private String profile_url;
	private String fb_url;
	private String tw_url;
	private String location;
	
	public void set_author_name(String author_name){
		this.author_name = author_name;
	}
	
	public void set_affiliation(String affiliation){
		this.affiliation = affiliation;
	}
	
	public void set_research_fields(ArrayList<String> research_fields){
		this.research_fields = research_fields;
	}
	
	public void set_n_of_citations(int n_of_citations){
		this.n_of_citations = n_of_citations;
	}
	
	public void set_photo_url(String photo_url){
		this.photo_url = photo_url;
	}
	
	public void set_profile_image(String profile_image){
		this.image_url = profile_image;
	}
	
	public void set_profile_url(String profile_url){
		this.profile_url = profile_url;
	}
	
	public void set_fb_url(String fb_url){
		this.fb_url = fb_url;
	}
	
	public void set_tw_url(String tw_url){
		this.tw_url = tw_url;
	}
	
	public void set_location(String location){
		this.location = location;
	}
	
	//getters
	public String get_author_name(){
		return this.author_name;
	}
	
	public String get_affiliation(){
		return this.affiliation;
	}
	
	public ArrayList<String> get_research_fields(){
		return this.research_fields;
	}
	
	public int get_n_of_citations(){
		return this.n_of_citations;
	}
	
	public String get_photo_url(){
		return this.photo_url;
	}
	
	public String get_image_url(){
		return this.image_url;
	}
	
	public String get_profile_url(){
		return this.profile_url;
	}
	
	public String get_fb_url(){
		return this.fb_url;
	}
	
	public String get_tw_url(){
		return this.tw_url;
	}
	
	public String get_location(){
		return this.location;
	}
}
