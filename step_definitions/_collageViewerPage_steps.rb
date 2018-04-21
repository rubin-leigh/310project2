Given(/^we are on the collage viewer page$/) do
   visit "https://localhost:8443/310Project2/Login.jsp"
   fill_in('uName', :with => 'dan')
   fill_in('pWord', :with => 'password')
   find_button('loginButton').click
   page.driver.browser.manage.window.maximize
end

When(/^we create a new collage "([^"]*)"$/) do |arg1|
  fill_in('topic', :with => arg1)
  fill_in('shape', :with => "t")
  find_button('submitButton').click
end

Then(/^I should see the main collage viewer has a collage$/) do
  page.find_by_id("mainCollage")
end

When(/^click the save button$/) do
  first("input[value=Save]").click
end

Then(/^we click SaveToHistoryButton$/) do
  find_button('saveButton').click
end

Then(/^we should see the collage "([^"]*)" in the PreviousCollageViewer$/) do |arg1|
  page.find_by_id("0div")
end

When(/^log out$/) do
  find_button('logoutButton').click
end

Then(/^I should be on the login page$/) do
  current_path.should == "310Project2/CollageViewerPage.jsp"
end

When(/^log in$/) do
   fill_in('uName', :with => 'dan')
   fill_in('pWord', :with => 'password')
   find_button('loginButton').click
end

When(/^click the collage "([^"]*)" in the PreviousCollageViewer$/) do |arg1|
  page.find_by_id("0div").click
end

When(/^click the delete button$/) do
  find_button('deleteButton').click
end

Then(/^we should NOT see the collage "([^"]*)" in the PreviousCollageViewer$/) do |arg1|
  page.should have_no_content(arg1)
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
  expect(page.find('#mainCollage')['alt']).to have_content arg1
end

Then(/^SaveToHistoryButton shouldn't be clickable$/) do
  page.find_by_id("saveButton", visible: false)
end

Then(/^we should see the PreviousCollageViewer has one new element$/) do
  page.find_by_id("0div")
end

Then(/^we should see a loading image$/) do
  pending # Write code here that turns the phrase above into concrete actions
end

Then(/^I should see error text appear$/) do
  #TODO expect(page).to have_css("div#title", text: "ERROR TEXT")
end

Then(/^the main collage should display the collage for "([^"]*)"$/) do |arg1|
   expect(page.find('#mainCollage')['alt']).to have_content arg1
end

When(/^I hit black and white$/) do
  choose('blackAndWhite')
end

Then(/^I should see the correct black and white collage adjustment$/) do
  expect(page.find('#mainCollage')['alt']).to have_content "b&w"
end

When(/^I hit sepia$/) do
  choose('sepia')
end

Then(/^I should see the correct sepia collage adjustment$/) do
  expect(page.find('#mainCollage')['alt']).to have_content "sepia"
end

When(/^I hit none$/) do
  choose('none')
end

Then(/^I should see the correct none collage adjustment$/) do
  expect(page.find('#mainCollage')['alt']).to have_content "none"
end

When(/^I hit grayscale$/) do
  choose('grayscale')
end

Then(/^I should see the correct grayscale collage adjustment$/) do
  expect(page.find('#mainCollage')['alt']).to have_content "grayscale"
end

Then(/^I should see the height input box$/) do
 page.find_by_id("height", visible: true)
end

Then(/^I should see the width input box$/) do
  page.find_by_id("width", visible: true)
end

When(/^I enter (\d+) in the height input box$/) do |arg1|
  # Write code here that turns the phrase above into concrete actions
end

When(/^I enter (\d+) in the width input box$/) do |arg1|
  # Write code here that turns the phrase above into concrete actions
end

Then(/^I should see the collage has a height and width corresponding to those values$/) do
  expect(page.find('#mainCollage')['alt']).to have_content "50"
end


