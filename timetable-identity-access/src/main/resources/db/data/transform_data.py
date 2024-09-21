import pandas as pd

# Load the CSV files
permissions_df = pd.read_csv('permissions_data.csv')
roles_df = pd.read_csv('roles_data.csv')

# Strip the '_ROLE' suffix from the 'name' column in roles_df to match the 'type' column in permissions_df
roles_df['name'] = roles_df['name'].str.replace('_ROLE', '')

# Merge permissions with roles based on 'group' in permissions and 'name' in roles
merged_df = pd.merge(permissions_df, roles_df, left_on='type', right_on='type')

# Select only relevant columns for the output
output_df = merged_df[['id_y', 'id_x']]

# Rename columns to match the required format
output_df.columns = ['role_id', 'permission_id']

# Save the result to a new CSV file
output_df.to_csv('role_permissions_data.csv', index=False)

print("File 'role_permissions_data.csv' has been created.")
