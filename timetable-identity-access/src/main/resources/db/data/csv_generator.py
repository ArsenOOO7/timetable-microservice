from uuid import uuid4
import pandas

names = []
for i in range(0, 4):
    names.append(str(uuid4()))

df = pandas.DataFrame(names, columns=['id'])
df.to_csv('users_ids.csv', index=False)