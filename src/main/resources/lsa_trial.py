from __future__ import print_function
from tabulate import tabulate
import sklearn
import readrequirement
import testcaseread
from sklearn.decomposition import TruncatedSVD
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.preprocessing import Normalizer
from sklearn import metrics
from sklearn.cluster import KMeans, MiniBatchKMeans
import pandas as pd
import warnings
import numpy as np
import json
warnings.filterwarnings("ignore")
example =[]


readrequirement.readRequirementFromTextFile()
testCaseId = []

testCaseId = testcaseread.readTestCasesFromExcelFile()

with open("/opt/apache-tomcat-8.0.36/webapps/resources1/textrequirementprocessed.txt", 'r') as f:
    example1 = f.readlines()

for i in range(len(example1)):
    example.append(example1[i].strip('\n'))

vectorizer = CountVectorizer(min_df = 2, stop_words = 'english',strip_accents = 'ascii')
dtm = vectorizer.fit_transform(example)
#pd.DataFrame(dtm.toarray(),index=example,columns=vectorizer.get_feature_names()).head(10)

vectorizer.get_feature_names()

lsa = TruncatedSVD(2, algorithm = 'arpack')
dtm_lsa = lsa.fit_transform(dtm)
dtm_lsa = Normalizer(copy=False).fit_transform(dtm_lsa)
# Compute document similarity using LSA components
similarity = np.asarray(np.asmatrix(dtm_lsa) * np.asmatrix(dtm_lsa).T)
#if similarity > 0.7:
#print similarity
df = pd.DataFrame(similarity).head(1)
resultlist = []
resultlistdouble = []
for i in range(0,1):
    for j in range(0,len(example)):
        if df.get_value(i,j)>0.9:
            resultlist.append("Test Case is Relevant "+ str(j))
            resultlistdouble.append(df.get_value(i,j))
        else:
            resultlist.append("Test Case is Non- Relevant "+ str(j))
            resultlistdouble.append(df.get_value(i,j))

resultlist.pop(0)
resultlistdouble.pop(0)
#testCaseId.extend(resultlistdouble)
def create_dict(keys, values):
    return dict(zip(keys, values + [None] * (len(keys) - len(values))))

dict_1 = {}
dict_1 = create_dict(testCaseId,resultlistdouble)
print (json.dumps(dict_1))

 


