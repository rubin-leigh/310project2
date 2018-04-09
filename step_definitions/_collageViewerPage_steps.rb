Given(/^we are on the collage viewer page$/) do
   visit "https://localhost:8443/310Project2/CollageViewerPage.jsp"
   page.driver.browser.manage.window.maximize
end

When(/^we create a new collage "([^"]*)"$/) do |arg1|
  fill_in('topic', :with => arg1)
  fill_in('shape', :with => "test")
  find_button('submitButton').click
end

Then(/^I should see the main collage viewer has a collage$/) do
  #TODO probably an expect page
end

When(/^click the save button$/) do
  first("input[value=Save]").click
  #TODO may not work, we'll test it
end

Then(/^we click SaveToHistoryButton$/) do
  find_button('saveButton').click
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

When(/^click the collage "([^"]*)" in the PreviousCollageViewer$/) do |arg1|
  #first("img[alt="+arg1+"]").click
  #TODO This might change depending on how the prevcollage box is set up
end

When(/^click the delete button$/) do
  find_button('deleteButton').click
end

Then(/^we should NOT see the collage "([^"]*)" in the PreviousCollageViewer$/) do |arg1|
  pending # Write code here that turns the phrase above into concrete actions
end

Then(/^I should see there is a radio button with the black and white option$/) do
  page.find_by_id("blackAndWhite", visible: true)
  choose('blackAndWhite')
end

Then(/^I should see there is a radio button with the sepia option$/) do
  page.find_by_id("sepia", visible: true)
  choose('sepia')
end

Then(/^I should see there is a radio button with the grayscale option$/) do
  page.find_by_id("grayscale", visible: true)
  choose('grayscale')
end

Then(/^I should see there is a radio button with the none option$/) do
  page.find_by_id("none", visible: true)
  choose('none')
end

Then(/^I should see there is a radio button with the on option for rotation$/) do
  page.find_by_id("rotateOn", visible: true)
  choose('rotateOn')
end

Then(/^I should see there is a radio button with the off option for rotation$/) do
  page.find_by_id("rotateOff", visible: true)
  choose('rotateOff')
end

Then(/^I should see there is a radio button with the on option for photo border$/) do
  page.find_by_id("borderOn", visible: true)
  choose('borderOn')
end

Then(/^I should see there is a radio button with the off option for photo border$/) do
  page.find_by_id("borderOff", visible: true)
  choose('borderOff')
end

Then(/^I should see the input box has placeholder equal to enter topic$/) do
  expect(find_field('topic')['placeholder']).to eq 'Enter Topic'
end


Then(/^I should see there is a button called export$/) do
  page.find_by_id("exportButton", visible: true)
end

Then(/^I should see there is a radio button called png$/) do
  pending # Write code here that turns the phrase above into concrete actions
end

Then(/^I should see there is a radio button called pdf$/) do
  pending # Write code here that turns the phrase above into concrete actions
end

Then(/^the current collage should have alt text "([^"]*)"$/) do |arg1|
  pending # Write code here that turns the phrase above into concrete actions
end

Then(/^SaveToHistoryButton shouldn't be clickable$/) do
  page.find_by_id("saveButton", visible: false)
end

Then(/^we should see the PreviousCollageViewer has one new element$/) do
  pending # Write code here that turns the phrase above into concrete actions
end

Then(/^we should see a loading image$/) do
  pending # Write code here that turns the phrase above into concrete actions
end

Then(/^I should see error text appear$/) do
  #TODO expect(page).to have_css("div#title", text: "ERROR TEXT")
end

Then(/^the main collage should display the collage for "([^"]*)"$/) do |arg1|
  #TODO check the alt value of the collage
end

