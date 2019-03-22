# GraphScholar
Recent Knowledge Graphs (KGs) like Wikidata and YAGO are often constructed by 
incorporating knowledge from semi-structured heterogeneous data resources such 
as Wikipedia. However, despite their large amount of knowledge, these graphs are 
still incomplete. In this project, we posit that Online Social Networks (OSNs) 
can become prominent data resources comprising abundant knowledge about real-world
entities. An entity on an OSN is represented by a profile; the link to this profile
is called a social link. In this paper, we propose a KG refinement method for adding
missing knowledge to a KG, i.e., social links. We target specific entity types, 
in the scientific community, such as researchers. Our approach uses both scholarly
data resources and existing KG for building knowledge bases. Then, it matches this
knowledge with OSNs to detect the corresponding social link(s) for a specific entity. 
It uses a novel matching algorithm, in combination with supervised and unsupervised 
learning methods. We empirically validate that our system is able to detect a large 
number of social links with high confidence.
