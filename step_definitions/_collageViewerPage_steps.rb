Then(/^we should NOT see the collage "([^"]*)" in the PreviousCollageViewer$/) do |arg1|
  pending # Write code here that turns the phrase above into concrete actions
end

Then(/^I should see there is a radio button called "([^"]*)"$/) do |arg1|
  page.find_by_id(arg1, visible: true)
end

Then(/^I should see the input box has placeholder equal to "([^"]*)"$/) do |arg1|
  expect(find_field('topic')['placeholder']).to eq 'Enter Topic'
end

Then(/^the current collage should have alt text "([^"]*)"$/) do |arg1|
  pending # Write code here that turns the phrase above into concrete actions
end

Then(/^I should see there is a button called "([^"]*)"$/) do |arg1|
  page.find_by_id(arg1, visible: true)
end

Then(/^SaveToHistoryButton shouldn't be clickable$/) do
  page.find_by_id("SaveToHistoryButton", visible: false)
end

Then(/^we click SaveToHistoryButton$/) do
  find_field('SaveToHistoryButton').click
end

Then(/^we should see the PreviousCollageViewer has one new element$/) do
  pending # Write code here that turns the phrase above into concrete actions
end

Then(/^we should see a loading image$/) do
  pending # Write code here that turns the phrase above into concrete actions
end

#-------------------------------------------------------------------------------------

Given(/^we are on the collage viewer page$/) do
   visit "http://localhost:8080/310project2/CollageViewerPage.jsp"
   page.driver.browser.manage.window.maximize
end

When(/^we create a new collage "([^"]*)"$/) do |arg1|
  fill_in('topic', :with => arg1)
  fill_in('topic', :with => "test")
  find_field("topic").native.send_key(:enter)
end

Then(/^I should see error text appear$/) do
  #TODO expect(page).to have_css("div#title", text: "ERROR TEXT")
end

Then(/^I should see the main collage viewer has a collage$/) do
  #TODO probably an expect page
end

When(/^click the save button$/) do
  first("input[value=Save]").click
  #TODO may not work, we'll test it
end

Then(/^we should see the collage "([^"]*)" in the PreviousCollageViewer$/) do |arg1|
  #TODO check the alt value of the collage viewer
end

When(/^log out$/) do
  first("input[value=Logout]").click
end

When(/^log in$/) do
  #just a few fill_ins and a find_field+enter
end

When(/^we create the collage "([^"]*)"$/) do |arg1|
  fill_in('topic', :with => arg1)
  fill_in('topic', :with => "test")
  find_field("topic").native.send_key(:enter)
end

When(/^click the collage "([^"]*)" in the PreviousCollageViewer$/) do |arg1|
  first("img[alt="+arg1+"]").click
  #TODO This might change depending on how the prevcollage box is set up
end

Then(/^the main collage should display the collage for "([^"]*)"$/) do |arg1|
  #TODO check the alt value of the collage
end

When(/^click the delete button$/) do
  first("input[alt=Delete]").click
end

