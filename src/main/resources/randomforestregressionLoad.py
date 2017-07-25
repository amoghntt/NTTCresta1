from sklearn.externals import joblib
import json
import sys
import string
listInput = sys.argv[1]

resultInputInt = map(int, listInput.strip('[]').split(','))

regressor = joblib.load('/opt/apache-tomcat-8.0.36/webapps/resources1/usecase4/randomforestregression.pkl')

predictedProjectStatus= regressor.predict([resultInputInt])

availabilityOfBudget = resultInputInt[15]

adaptiveBudget = float((predictedProjectStatus.astype(float)/3)*availabilityOfBudget)

adaptiveBudget = round(adaptiveBudget,2)

predictedProjectStatus = round(float(predictedProjectStatus),2)

resultList = []

resultList.append(predictedProjectStatus)

resultList.append(adaptiveBudget)

print (json.dumps(list(resultList)))
