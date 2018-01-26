package net.oschina.htmlsucker;

public class HeuristicString {

    private String string;

    public HeuristicString(String string) throws CandidateFound {
        this.string = string;
        if (string != null && !string.isEmpty()) {
            throw new CandidateFound(string);
        }
    }

    public HeuristicString or(String candidate) throws CandidateFound {
        if (candidate != null) {
            if (string == null || string.isEmpty()) {
                string = candidate;
            } else {
                throw new CandidateFound(string);
            }
        }
        return this;
    }

    @Override
    public String toString() {
        return string;
    }

    public static class CandidateFound extends Exception {
        public final String candidate;

        public CandidateFound(String candidate) {
            this.candidate = candidate;
        }
    }

}
