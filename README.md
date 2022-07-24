# IdeaSwiftformat

![Build](https://github.com/dankinsoid/IdeaSwiftformat/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/PLUGIN_ID.svg)](https://plugins.jetbrains.com/plugin/PLUGIN_ID)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/PLUGIN_ID.svg)](https://plugins.jetbrains.com/plugin/PLUGIN_ID)

<!-- Plugin description -->
This plugin provides integration with [`Swiftformat` commmands](https://docs.swiftformat.io/commands).

## Usage
Commands are available in <kbd>Tools</kbd> > <kbd>Swiftformat</kbd> menu. It's recommended to add keymaps for these commands.
While editing a manifest projects `swiftformat generate` calls automatically on saving, as well as `swiftformat fetch` while editing the `Dependencies.swift`.

## Available commands
- Generate - [`swiftformat generate -n`](https://docs.swiftformat.io/commands/generate).
- Edit Manifests - [`swiftformat edit`](https://docs.swiftformat.io/commands/edit) in `AppCode`.
- Fetch Dependencies - [`swiftformat fetch`](https://docs.swiftformat.io/commands/dependencies) and [`swiftformat generate -n`](https://docs.swiftformat.io/commands/generate).
- Update Dependencies - [`swiftformat fetch --update`](https://docs.swiftformat.io/commands/dependencies) and [`swiftformat generate -n`](https://docs.swiftformat.io/commands/generate).
- Clean Dependencies - [`swiftformat clean dependencies`](https://docs.swiftformat.io/commands/dependencies).
- Build - [`swiftformat build`](https://docs.swiftformat.io/commands/build).
- Clean - [`swiftformat clean`](https://docs.swiftformat.io/commands/clean).
- Test - [`swiftformat test`](https://docs.swiftformat.io/commands/test).
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
