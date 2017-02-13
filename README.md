# MotionAiClient

This app allows you to interact with [motion.ai](https://www.motion.ai/) bots that I've created.

# Change Log
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/)

## 2017-02-10
### Fixed
- Correctly displays modules that do not have any quick replies associated to them

## 2017-02-09
### Added
- Bot respects ::next:: tags in messages
- Rounded images

### Fixed
- Pre api21 (Kitkat and below) devices properly apply bot colour to speech bubbles and card buttons

## 2017-02-06
### Added
- Full YouTube tag support
- Quick replies to remove themselves once no longer relevent
- Card subtitle space reduced when there's no subtitle to display
### Changed
- Chat bubble UI tweaks
- Quick replies float to the right hand side

## 2017-01-29
### Added
- Bot icon next to messages from bot
- Media controls to video speech bubbles
### Changed
- Conversation now styled to suit bot
- Inversed colours from before (now the bot has the coloured speech bubbles and the user has the white speech bubbles)
- Images are no longer enclosed within a speech bubble
- Videos are no longer enclodes within a speech bubble
- Videos take up the width of the screen for easier viewing


## 2017-01-27
### Fixed
- Crash when following a link from a card

## 2017-01-27
### Added
- Theme and styling set to look like a typical Orange branded app
- Video tag support.
	- Vidoes formatted like [video]video_url_here[/video]
	- Html video tags not supported in message
	- Must point to a direct video resource or it'll fail (i.e. youtube links don't go here)
- YouTube tag support
	- formatted like [youtube]youtuble_url_here[/youtube]
	- placeholders for now, has a button to point towards site
- Card actions and image urls handled
- Card buttons correctly hide when unused
- Bot "starts" the conversation when the app starts (app introduces self, but hidden from user)
- Button to clear the chat log
- Button to restart the chat
- Images, videos and youtube links are now parsed out and displayed properly


## 2017-01-25
### Added
- Quick replies
- Cards
- Images

