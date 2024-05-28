from rdflib import Graph
from pyfuseki import FusekiUpdate

g = Graph()

g.parse('./data/rdf/person_metadata.nt')
#g.parse('./data/rdf/person_metadata.rdf')
fuseki = FusekiUpdate('http://147.91.177.197:80', 'fromFile');
fuseki.insert_graph(g)