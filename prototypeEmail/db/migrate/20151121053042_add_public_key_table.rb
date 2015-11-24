class AddPublicKeyTable < ActiveRecord::Migration
  def change
  	create_table(:public_keys) do |p|
  		p.integer :user_id
  		p.string :key
  	end
  end
end
