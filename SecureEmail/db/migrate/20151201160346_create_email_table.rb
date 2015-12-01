class CreateEmailTable < ActiveRecord::Migration
  def change
    create_table :emails do |t|
      t.string :data
      t.integer :to_user, null: false
      t.integer :from_user, null: false
      t.integer :read
    end
  end
end
