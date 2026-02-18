# Releasing

This document describes the steps to prepare and publish a new release of `@robbywh/react-native-zendesk-messaging`.

## Pre-release Checklist

Before releasing, make sure all of the following are complete:

### 1. Install Dependencies

```sh
yarn
```

Ensure all dependencies (including `release-it`) are installed and up to date.

### 2. Code Quality

```sh
yarn typecheck   # ensure no TypeScript errors
yarn lint        # ensure no linting issues
yarn test        # ensure all tests pass
```

### 3. Test on Example App

Run the example app on both platforms to verify your changes work correctly:

```sh
yarn example start       # start Metro bundler
yarn example android     # run on Android
yarn example ios         # run on iOS
```

### 4. Branch & Commits

- Ensure all changes are committed and pushed to `main`.
- All commits follow [Conventional Commits](https://www.conventionalcommits.org/en):
  - `fix:` for bug fixes
  - `feat:` for new features
  - `docs:` for documentation changes
  - `chore:` for tooling/config changes
- Commit subjects must be **lowercase** (enforced by commitlint).

### 5. Review Changes

```sh
git log --oneline $(git describe --tags --abbrev=0)..HEAD
```

Review all commits since the last release to confirm everything is ready.

### 6. Clean Build

```sh
yarn clean
```

Ensure no stale build artifacts remain.

---

## Release

This project uses [release-it](https://github.com/release-it/release-it) to automate the release process.

### Run the Release

```sh
yarn release
```

This will interactively guide you through:

1. **Version bump** — choose the next version (patch, minor, or major)
2. **Changelog** — auto-generates a changelog from conventional commits
3. **Git commit & tag** — commits `chore: release <version>` and creates tag `v<version>`
4. **npm publish** — publishes the package to the npm registry
5. **GitHub release** — creates a GitHub release with the changelog

### Skip Interactive Prompts

You can specify the version bump directly:

```sh
yarn release --patch   # e.g. 1.1.4 → 1.1.5
yarn release --minor   # e.g. 1.1.4 → 1.2.0
yarn release --major   # e.g. 1.1.4 → 2.0.0
```

### Dry Run

To preview what will happen without making any changes:

```sh
yarn release --dry-run
```

---

## Post-release

- Verify the package on [npm](https://www.npmjs.com/package/@robbywh/react-native-zendesk-messaging)
- Verify the [GitHub release](https://github.com/RobbyWH/react-native-zendesk-messaging/releases)
- Update any downstream projects that depend on this package
