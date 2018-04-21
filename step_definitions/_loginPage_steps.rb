Given(/^we are on the login page$/) do
  visit "https://localhost:8443/310Project2/Login.jsp"
end

When(/^I enter a correct username$/) do
  fill_in('uName', :with => 'dan')
end

When(/^I enter a corresponding correct password for that username$/) do
  fill_in('pWord', :with => 'password')
end

Then(/^click Log In button$/) do
  find_button('loginButton').click
end

Then(/^I should be on the CollageViewerPage$/) do
  expect(page).to have_content("Collage")
end

When(/^I enter an incorrect username$/) do
  fill_in('uName', :with => 'halfond')
end

When(/^I enter an incorrect password$/) do
  fill_in('pWord', :with => 'csrules')
end

Then(/^I should still be on the login page$/) do
  expect(page).to have_content("Invalid")
end
