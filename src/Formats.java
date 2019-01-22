
public class Formats {
		
		@SuppressWarnings("unused")
		private static String place_of_birth = "http://www.wikidata.org/prop/direct/P19";
		private static String country_of_citizinship = "http://www.wikidata.org/prop/direct/P27";
		@SuppressWarnings("unused")
		private static String description = "http://schema.org/description";
		@SuppressWarnings("unused")
		private static String occupation = "http://www.wikidata.org/prop/direct/P106";
		private static String affiliation = "http://www.wikidata.org/prop/direct/P108";
		private static String id = "http://www.wikidata.org/entity";
		@SuppressWarnings("unused")
		private static String dob = "http://www.wikidata.org/prop/direct/P569";
		private static String label = "http://www.w3.org/2000/01/rdf-schema#label";
		private static String fbURL = "http://www.wikidata.org/prop/direct/P191";
		private static String twURL = "http://www.wikidata.org/prop/direct/P200";
		private static String citations = "http://www.wikidata.org/prop/direct/P192";
		
		public static void main(String args[])
		{
			
		}
		
		public void string_to_ntriples(String s)
		{
			
		}
		
		public String object_to_ntriples(GoogleScholar_Author gsa)
		{
			
			String NTriples = "";
			
			int rand = (int) (Math.random()*5250);
			
			NTriples += "<" + id + "/Q" + rand + "> ";
			NTriples += "<" + affiliation + "> ";
			NTriples += "<" + id + "/Q123456" + "> . \n";
			
			NTriples += "<" + id + "/Q" + rand + "> ";
			NTriples += "<" + country_of_citizinship + "> ";
			NTriples += "<" + id + "/Q78910111" + "> . \n";
			
			NTriples += "<" + id + "/Q" + rand + "> ";
			NTriples += "<" + label + "> ";
			NTriples += "\"" + gsa.get_author_name() + "\" . \n";
			
			NTriples += "<" + id + "/Q" + rand + "> ";
			NTriples += "<" + fbURL + "> "; 
			NTriples += "<" + gsa.get_fb_url() + "> . \n";
			
			NTriples += "<" + id + "/Q" + rand + "> ";
			NTriples += "<" + citations + "> "; 
			NTriples += gsa.get_n_of_citations() + " . \n";
			
			NTriples += "<" + id + "/Q" + rand + "> ";
			NTriples += "<" + twURL + "> "; 
			NTriples += gsa.get_tw_url() + " . \n";
			
			return NTriples;
		}

}
