class AddUsernamesAndKeysToUsers < ActiveRecord::Migration
  def change
    add_column :users, :public_key, :string
    add_column :users, :username, :string
  end
end
