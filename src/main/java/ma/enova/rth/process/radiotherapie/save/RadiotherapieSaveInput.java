package ma.enova.rth.process.radiotherapie.save;

import ma.enova.rth.dto.SeanceRadiotherapieDto;
import ma.enova.rth.process.radiotherapie.util.RadiotherapieInput;

import java.util.List;

public class RadiotherapieSaveInput extends RadiotherapieInput {
    private List<SeanceRadiotherapieDto> seanceRadiotherapies;

    public List<SeanceRadiotherapieDto> getSeanceRadiotherapies() {
        return seanceRadiotherapies;
    }

    public void setSeanceRadiotherapies(List<SeanceRadiotherapieDto> seanceRadiotherapies) {
        this.seanceRadiotherapies = seanceRadiotherapies;
    }
}
