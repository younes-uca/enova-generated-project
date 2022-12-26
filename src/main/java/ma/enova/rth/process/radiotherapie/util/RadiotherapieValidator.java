package ma.enova.rth.process.radiotherapie.util;

import ma.enova.rth.zynerator.process.Result;
import ma.enova.rth.zynerator.util.DateUtil;
import ma.enova.rth.zynerator.util.StringUtil;
import ma.enova.rth.dto.FrequenceRadiotherapieDto;

public class RadiotherapieValidator {


    public static void validateFraction(Integer fractionnement, Result result) {
        if (fractionnement == null || fractionnement <= 0) {
            result.addErrorMessage("error.radiotherapie.commun.fractionnement.stritectement.positif");
        }
    }

    public static void validateFrequenceRadio(FrequenceRadiotherapieDto frequenceRadiotherapie, Result result) {
        if (frequenceRadiotherapie == null || StringUtil.isEmpty(frequenceRadiotherapie.getCode())) {
            result.addErrorMessage("error.radiotherapie.commun.frequenceRadiotherapie.obligatoire");
        }
    }

    public static void validateDateTraitement(String dateSouhaiteDebutTraitement, Result result) {
        if (StringUtil.isEmpty(dateSouhaiteDebutTraitement)) {
            result.addErrorMessage("error.radiotherapie.commun.dateDebutTraitement.obligatoire");
        } else if (DateUtil.isBeforeNow(dateSouhaiteDebutTraitement)) {
            result.addErrorMessage("error.radiotherapie.commun.dateDebutTraitement.inferieur.aujoudhui");
        }
    }

}
