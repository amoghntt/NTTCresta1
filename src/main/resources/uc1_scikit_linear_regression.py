import json
import sys

import mysql.connector    
from pandas import DataFrame
from sklearn.linear_model import LinearRegression


cnx = mysql.connector.connect(user='cresta', password='cresta',
                              host='10.248.3.91',
                           database='cresta')
cols = ['KLOC', 'test_case_count', 'application_complexity', 'domain_knowledge', 'technical_skills', 'requirements_query_count', 'code_review_comments', 'design_review_comments','defect_count/KLOC']
strColumns = ','.join(cols)
query = "select " + strColumns + " from usecase1 where pred_code=%s and userid=%s"
params = (sys.argv[1], sys.argv[2])
try:
   cursor = cnx.cursor()
   cursor.execute(query, params)
   data = DataFrame(cursor.fetchall(), columns=cols)
   input_data = data[cols[:-1]][-1:]
   data = data[:-1]
finally:
    cnx.close()

X = data[cols[:-1]]
data = data.rename(columns={'defect_count/KLOC': 'defect_density'})
y = data.defect_density
lm = LinearRegression()
lm.fit(X, y)

result = lm.predict(input_data)
print json.dumps(list(result))
