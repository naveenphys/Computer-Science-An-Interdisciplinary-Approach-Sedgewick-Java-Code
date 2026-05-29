
/* Program: PotentialGene
 * Description: Demostrates Single Responsibility Principle.
 *              Find out if the sequence of letters on the command is a 
 *              potential gene.
 *              
 *              Gene consists of a sequence of codons, each of which is a
 *              sequences of three bases (representing an amino acid). 
 *              ATG -> Start codon.
 *              TAG -> Stop codon.
 *              TAA -> Stop codon.
 *              TGA -> Stop codon.
 *              A stop codon cannot appear within the geme.
 * java PotentialGene.java ATGCGCCTGCGTCTGTACTAG
 */

public class PotentialGene {
    public static void main(String[] args) {
        String sequence = args[0];
        System.out.println(isPotentialGene(sequence, 3));
    }

    static boolean isPotentialGene(String gene, int codon_size) {
        return hasValidLength(gene, codon_size)
                && hasValidStart(gene)
                && hasValidEnd(gene)
                && hasValidContent(gene, codon_size);
    }

    static boolean hasValidLength(String gene, int codon_size) {
        return gene.length() % codon_size == 0;
    }

    static boolean hasValidStart(String gene) {
        return gene.startsWith("ATG");
    }

    static boolean hasValidEnd(String gene) {
        return gene.endsWith("TAG")
                || gene.endsWith("TAA")
                || gene.endsWith("TGA");
    }

    static boolean isAdenine(char c) {
        return c == 'A';
    }

    static boolean isGuanine(char c) {
        return c == 'G';
    }

    static boolean isThymine(char c) {
        return c == 'T';
    }

    static boolean isCytosine(char c) {
        return c == 'C';
    }

    static boolean isValidCodon(String codon) {
        for (int i = 0; i < codon.length(); i++) {
            char c = codon.charAt(i);
            if (!isAdenine(c) && !isGuanine(c)
                    && !isThymine(c) && !isCytosine(c))
                return false;
        }
        return true;
    }

    static boolean hasValidContent(String gene, int codon_size) {
        for (int i = codon_size; i < gene.length() - codon_size; i += codon_size) {
            if (!isValidCodon(gene.substring(i, i + codon_size)))
                return false;
            if (gene.regionMatches(i, "TAG", 0, codon_size))
                return false;
            if (gene.regionMatches(i, "TAA", 0, codon_size))
                return false;
            if (gene.regionMatches(i, "TGA", 0, codon_size))
                return false;
        }
        return true;
    }

}
