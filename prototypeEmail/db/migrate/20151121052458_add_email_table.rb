class AddEmailTable < ActiveRecord::Migration
  def change
  	add_column :emails, :to_user, :integer
  	add_column :emails, :from_user, :ingeter
  	add_column :emails, :data, :text
  end
end
