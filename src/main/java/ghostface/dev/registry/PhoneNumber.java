package ghostface.dev.registry;


import org.jetbrains.annotations.NotNull;

public final class PhoneNumber {

    private final @NotNull DDD ddd;
    private final int number;

    public PhoneNumber(@NotNull DDD dd, int number) {
        @NotNull String string = String.valueOf(number);
        if (string.length() != 9 || Integer.parseInt(String.valueOf(string.charAt(0))) != 9) throw new IllegalArgumentException("Invalid Phone Number: " + number);
        this.ddd = dd;
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public long getCompleteNumber() {
        return Long.parseLong(String.valueOf(ddd.code) + number);
    }

    public @NotNull DDD getDdd() {
        return ddd;
    }

    public enum DDD {
        SAO_PAULO(11, "SP"),
        SAO_PAULO_12(12, "SP"),
        SAO_PAULO_13(13, "SP"),
        SAO_PAULO_14(14, "SP"),
        SAO_PAULO_15(15, "SP"),
        SAO_PAULO_16(16, "SP"),
        SAO_PAULO_17(17, "SP"),
        SAO_PAULO_18(18, "SP"),
        SAO_PAULO_19(19, "SP"),
        RIO_DE_JANEIRO(21, "RJ"),
        RIO_DE_JANEIRO_22(22, "RJ"),
        RIO_DE_JANEIRO_24(24, "RJ"),
        ESPIRITO_SANTO_27(27, "ES"),
        ESPIRITO_SANTO_28(28, "ES"),
        MINAS_GERAIS_31(31, "MG"),
        MINAS_GERAIS_32(32, "MG"),
        MINAS_GERAIS_33(33, "MG"),
        MINAS_GERAIS_34(34, "MG"),
        MINAS_GERAIS_35(35, "MG"),
        MINAS_GERAIS_37(37, "MG"),
        MINAS_GERAIS_38(38, "MG"),
        PARANA_41(41, "PR"),
        PARANA_42(42, "PR"),
        PARANA_43(43, "PR"),
        PARANA_44(44, "PR"),
        PARANA_45(45, "PR"),
        PARANA_46(46, "PR"),
        SANTA_CATARINA_47(47, "SC"),
        SANTA_CATARINA_48(48, "SC"),
        SANTA_CATARINA_49(49, "SC"),
        RIO_GRANDE_DO_SUL_51(51, "RS"),
        RIO_GRANDE_DO_SUL_53(53, "RS"),
        RIO_GRANDE_DO_SUL_54(54, "RS"),
        RIO_GRANDE_DO_SUL_55(55, "RS"),
        DISTRITO_FEDERAL_61(61, "DF"),
        GOIAS_62(62, "GO"),
        TOCANTINS_63(63, "TO"),
        GOIAS_64(64, "GO"),
        MATO_GROSSO_65(65, "MT"),
        MATO_GROSSO_66(66, "MT"),
        MATO_GROSSO_DO_SUL_67(67, "MS"),
        ACRE_68(68, "AC"),
        RONDONIA_69(69, "RO"),
        BAHIA_71(71, "BA"),
        BAHIA_73(73, "BA"),
        BAHIA_74(74, "BA"),
        BAHIA_75(75, "BA"),
        BAHIA_77(77, "BA"),
        SERGIPE_79(79, "SE"),
        PERNAMBUCO_81(81, "PE"),
        ALAGOAS_82(82, "AL"),
        PARAIBA_83(83, "PB"),
        RIO_GRANDE_DO_NORTE_84(84, "RN"),
        CEARA_85(85, "CE"),
        PIAUI_86(86, "PI"),
        PERNAMBUCO_87(87, "PE"),
        CEARA_88(88, "CE"),
        PIAUI_89(89, "PI"),
        PARA_91(91, "PA"),
        AMAZONAS_92(92, "AM"),
        PARA_93(93, "PA"),
        PARA_94(94, "PA"),
        RORAIMA_95(95, "RR"),
        AMAPA_96(96, "AP"),
        AMAZONAS_97(97, "AM"),
        MARANHAO_98(98, "MA"),
        MARANHAO_99(99, "MA");

        private final int code;
        private final String state;

        DDD(int code, String state) {
            this.code = code;
            this.state = state;
        }

        public int getCode() {
            return code;
        }

        public String getState() {
            return state;
        }
    }

}
