# AAC navigation lib crashes and issues

Issues:
- **FIXED in 1.0.0-alpha05** [`crash1`](crash1) - Crash after using `popUpTo` and navigating to same fragment twice - https://issuetracker.google.com/issues/112384846
- [`issue1`](issue1) - Deep link intent filter does not work with string resources - https://issuetracker.google.com/issues/112410865
- [`crash2`](crash2) - Crash when using deep link in a graph with includes and inclusive `popUpTo`, if this link was clicked in SMS app. It works fine with `adb shell am start ...`.
