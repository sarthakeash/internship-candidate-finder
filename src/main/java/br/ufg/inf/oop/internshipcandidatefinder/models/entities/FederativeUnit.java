package br.ufg.inf.oop.internshipcandidatefinder.models.entities;

import java.util.Arrays;

public enum FederativeUnit {
    ACRE("AC", "Acre"), ALAGOAS("AL", "Alagoas"), AMAPA("AP", "Amapa"), AMAZONAS("AM", "Amazonas"),
    BAHIA("BA", "Bahia"), CEARA("CE", "Ceará"), DISTRITO_FEDERAL("DF", "Distrito Federal"),
    ESPIRITO_SANTO("ES", "Espírito Santo"), GOIAS("GO", "Goiás"), MARANHAO("MA", "Maranhão"),
    MATO_GROSSO("MT", "Mato Grosso"), MATO_GROSSO_DO_SUL("MS", "Mato Grosso do Sul"),
    MINAS_GERAIS("MG", "Minas Gerais"), PARA("PA", "Ṕará"), PARAIBA("PB", "Paraíba"),
    PARANA("PR", "Paraná"), PERNAMBUCO("PE", "Pernambuco"), PIAUI("PI", "Piauí"),
    RIO_DE_JANEIRO("RJ", "Rio de Janeiro"), RIO_GRANDE_DO_NORTE("RN", "Rio Grande do Norte"),
    RIO_GRANDE_DO_SUL("RS", "Rio Grande do Sul"), RONDONIA("RO", "Rondônia"),
    RORAIMA("RR", "Roraima"), SANTA_CATARINA("SC", "Santa Catarina"), SAO_PAULO("SP", "São Paulo"),
    SERGIPE("SE", "Sergipe"), TOCANTINS("TO", "Tocantins");

    private final String initials;
    private final String name;

    FederativeUnit(String initials, String name) {
        this.initials = initials;
        this.name = name;
    }

    public String getInitials() {
        return initials;
    }

    public String getName() {
        return name;
    }

    public static FederativeUnit fromInitials(String initials) throws IllegalArgumentException {
        for (FederativeUnit uf : FederativeUnit.values()) {
            if (uf.initials.equalsIgnoreCase(initials)) {
                return uf;
            }
        }

        throw new IllegalArgumentException(String.format("No enum constant %s", initials));
    }

    public static FederativeUnit fromName(String name) {
        FederativeUnit federativeUnit = null;

        for (FederativeUnit uf : FederativeUnit.values()) {
            if (uf.name.equalsIgnoreCase(name)) {
                return uf;
            }
        }

        throw new IllegalArgumentException(String.format("No enum constant %s", name));
    }

    public static String valuesToString() {
        StringBuilder valuesToString = new StringBuilder();

        Arrays.asList(values()).forEach(uf -> valuesToString.append(uf).append("\n"));

        return valuesToString.toString();
    }

    @Override
    public String toString() {
        return String.format("%s - %s", name, initials);
    }
}
