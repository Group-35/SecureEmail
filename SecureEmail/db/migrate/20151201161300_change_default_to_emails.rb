class ChangeDefaultToEmails < ActiveRecord::Migration
  def change
    change_column :emails, :read, :integer, default: 0
  end
end
