== OF-DPA Platform configuration ==

This Package contains platform configuration for supported devices.

The configuration used is taken from the directory corresponding to the
platform name in ONL as detected during runtime.

== Adding new Platform Support ==

The configuration has two mandatory files:

* `config.bcm`
  Switch configuration

* `rc.soc`
  Platform specific switch initialization

=== config.bcm ===

The config.bcm format is mostly identical to the Switch SDK's. The `import`
directive is not supported.

The only OF-DPA requirement is an existing dport mapping. Only dport mapped
ports will be exposed as ports in OF-DPA.

=== rc.soc ===

May contain any amount of diag shell commands to execute on start up. Usually
contains instructions to program and enable the LED processor(s).

Basic switch initialization is already done by OF-DPA itself.

=== additional firmware files ===

Trident 3 and other CMICx based devices use a custom firmware for the LED
processor usually called `custom_led.bin`. This can be loaded in the `rc.soc`
via

```
m0 load 0 0x3800 /usr/share/ofdpa/platform/x86-64-vendor-device-r0/custom_led.bin
```

Since the firmware is a program protected by copyright, it needs to be
appropriately licensed and this licence needs to be documented in WHENCE.

=== custom LED data formats ===

To configure data supplied from OF-DPA to the LED firmware, OF-DPA supports a
custom `rc.soc` `led` subcommand `leddata`. It takes a path to a JSON encoded
file with a simple array of speed/value pairs.

See `/usr/share/data/ofdpa/led/cmicx_legacy.json` as an example for the format
and values used by Switch SDK 6.5.16 and earlier for CMICx platforms.
