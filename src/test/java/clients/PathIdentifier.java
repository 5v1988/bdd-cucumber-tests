package clients;

public enum PathIdentifier {
  ACCESS_TOKEN("/connect/token"),
  CREATE_BENEFICIARIES("/v1.0/beneficiaries");

  private final String path;

  PathIdentifier(String path) {
    this.path = path;
  }

  public String toString() {
    return this.path;
  }
}
