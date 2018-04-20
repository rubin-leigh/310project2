When(/^I hit the signup button on the login page$/) do
  find_button('signupButton').click
end

When(/^I type in unique credentials$/) do
  fill_in('uName', :with => 'nick')
  fill_in('pWord', :with => 'password')
end

When(/^I hit the signup button$/) do
  find_button('signupButton').click
end

When(/^I type in nonUnique credentials$/) do
  fill_in('uName', :with => 'dan')
  fill_in('pWord', :with => 'password')
end

Then(/^I should see that there is an error$/) do
  expect(page).to have_content("Invalid")
end

