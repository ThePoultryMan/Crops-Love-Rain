{
  inputs = {
    nixpkgs.url = "github:nixos/nixpkgs/nixos-25.11";
    flake-parts.url = "github:hercules-ci/flake-parts";
    systems.url = "github:nix-systems/default";
  };

  outputs = inputs:
    inputs.flake-parts.lib.mkFlake { inherit inputs; } {
      systems = import inputs.systems;

      perSystem = { config, self', pkgs, lib, system, ... }: let
        java25 = pkgs.javaPackages.compiler.temurin-bin.jdk-25;
        java21 = pkgs.javaPackages.compiler.temurin-bin.jdk-21;

        nativeBuildInputs = with pkgs; [
          java25
          java21
          git
        ];

        buildInputs = with pkgs; [
          (lib.getLib stdenv.cc.cc)
          glfw3-minecraft
          libGL
          libpulseaudio
          libx11
          libxcursor
        ];
      in {
        devShells.default = pkgs.mkShell {
          inherit nativeBuildInputs buildInputs;

          env = {
            LD_LIBRARY_PATH = lib.makeLibraryPath buildInputs;
            JDK21 = "${java21}";
            JAVA_HOME = "${java25.home}";
          };
        };
      };
    };
}
