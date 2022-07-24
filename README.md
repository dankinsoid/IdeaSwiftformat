# IdeaSwiftformat

![Build](https://github.com/dankinsoid/IdeaSwiftformat/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/PLUGIN_ID.svg)](https://plugins.jetbrains.com/plugin/PLUGIN_ID)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/PLUGIN_ID.svg)](https://plugins.jetbrains.com/plugin/PLUGIN_ID)

<!-- Plugin description -->
This plugin provides integration with [`SwiftFormat`](https://github.com/nicklockwood/SwiftFormat).

## Usage
Commands are available in <kbd>Tools</kbd> > <kbd>SwiftFormat</kbd> and <kbd>Refactor</kbd> > <kbd>SwiftFormat</kbd> menus. It's recommended to add keymaps for these commands.
While editing a manifest projects `swiftformat generate` calls automatically on saving, as well as `swiftformat fetch` while editing the `Dependencies.swift`.

## Available commands
- Format - `swiftformat .`.
- Format As Suggested - `swiftformat --inferoptions` and `swiftformat`.
<!-- Plugin description end -->

## License
This plugin is licensed under the terms of the GNU Public License version 3 or any later version.

## Credits
Plugin icon is merged icons of IdeaVim plugin and a random sneaker by FreePic from flaticon.com

## Installation

- Using IDE built-in plugin system:

  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "IdeaSwiftformat"</kbd> >
  <kbd>Install Plugin</kbd>

- Manually:

  Download the [latest release](https://github.com/dankinsoid/IdeaSwiftformat/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>

---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
