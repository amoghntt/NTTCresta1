import warnings
import testcaseread
warnings.filterwarnings("ignore")
import gensim
import scipy as sc
import numpy as np
import json
import nltk
import pprint as pprint
testcaseread.readTestCasesFromExcelFile()

def avg_feature_vector1(words, model, num_features):
        #function to average all words vectors in a given paragraph
        featureVec = np.zeros((num_features,), dtype="float32")
        nwords = 0

        #list containing names of words in the vocabulary
        index2word_set = set(model.index2word)
        for word in words:
            if word in index2word_set:
                nwords = nwords+1
                featureVec = np.add(featureVec, model[word])

        if(nwords>0):
            featureVec = np.divide(featureVec, nwords)
        return featureVec

with open('textrequirementprocessed.txt', 'r') as myfile:
    content = myfile.readlines()

content = [x.strip() for x in content]

resultArray = np.zeros((len(content),len(content)))
i = -1
j = -1

for sentence in content:
    i = i + 1
    j=-1
    for sentence1 in content:
        j = j + 1
        if (sentence is not sentence1):
            with open('result.txt', 'w') as myfile:
                myfile.write(sentence + "\n")
                myfile.write(sentence1)
            inputData1 = gensim.models.word2vec.LineSentence("result.txt")
            print("\n"+"\n"+"\n"+sentence+"\n"+sentence1)
            model = gensim.models.Word2Vec(inputData1, size=200, window=5, min_count=1, workers=1)
            sentence1AvgVector = avg_feature_vector1(sentence.split(), model, 200)
            sentence2AvgVector = avg_feature_vector1(sentence1.split(), model, 200)
            similarity = 1 - sc.spatial.distance.cosine(sentence1AvgVector,sentence2AvgVector)
            resultArray[i,j] = similarity
     
#thefile = open('textrequirementprocessed.txt', 'w')
#thefile.close()

#Convert numpy array to List
resultArraytoList = resultArray.tolist()
print (json.dumps(resultArraytoList))                        
