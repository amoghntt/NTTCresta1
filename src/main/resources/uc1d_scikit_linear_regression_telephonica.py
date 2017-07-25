import json
import sys

import mysql.connector    
from pandas import DataFrame
from sklearn.linear_model import LinearRegression

config = dict(line.strip().split('=') for line in open('/opt/apache-tomcat-8.0.36/webapps/resources1/config.properties'))
cnx = mysql.connector.connect(user=config.get("mysql_cresta_user"), password=config.get("mysql_cresta_password"),
                              host=config.get("mysql_host"),
                           database=config.get("db_name"))
cols = ['KLOC', 'test_case_count', 'application_complexity', 'domain_knowledge', 'technical_skills', 'requirements_query_count', 'code_review_comments', 'design_review_comments', 'defect_count']
strColumns = ','.join(cols)
query = "select " + strColumns + " from usecase1DTelephonica where tpr_name=%s and user_id=%s"
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
y = data.defect_count
lm = LinearRegression()
lm.fit(X, y)

result = lm.predict(input_data)
print json.dumps(list(result))
