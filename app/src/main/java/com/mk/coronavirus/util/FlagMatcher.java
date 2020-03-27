package com.mk.coronavirus.util;

import android.util.Log;

import com.mk.coronavirus.R;

public class FlagMatcher {
    public static int getFlag(String country) {
        switch (country) {
            case "China" : return R.drawable.china;
            case "Italy" : return R.drawable.italy;
            case "USA" : return R.drawable.usa;
            case "Spain" : return R.drawable.spain;
            case "Germany" : return R.drawable.germany;
            case "Iran" : return R.drawable.iran;
            case "France" : return R.drawable.france;
            case "Switzerland" : return R.drawable.switzerland;
            case "S. Korea" : return R.drawable.southkorea;
            case "UK" : return R.drawable.uk;
            case "Netherlands" : return R.drawable.netherlands;
            case "Austria" : return R.drawable.austria;
            case "Belgium" : return R.drawable.belgium;
            case "Norway" : return R.drawable.norway;
            case "Portugal" : return R.drawable.portugal;
            case "Sweden" : return R.drawable.sweden;
            case "Canada" : return R.drawable.canada;
            case "Australia" : return R.drawable.australia;
            case "Brazil" : return R.drawable.brazil;
            case "Israel" : return R.drawable.israel;
            case "Malaysia" : return R.drawable.malaysia;
            case "Denmark" : return R.drawable.denmark;
            case "Turkey" : return R.drawable.turkey;
            case "Czechia" : return R.drawable.cek;
            case "Japan" : return R.drawable.japan;
            case "Ireland" : return R.drawable.ireland;
            case "Ecuador" : return R.drawable.ecuador;
            case "Pakistan" : return R.drawable.pakistan;
            case "Chile" : return R.drawable.chile;
            case "Luxembourg" : return R.drawable.luxembourg;
            case "Thailand" : return R.drawable.thailand;
            case "Poland" : return R.drawable.poland;
            case "Finland" : return R.drawable.finland;
            case "Saudi Arabia" : return R.drawable.saudiarabia;
            case "Romania" : return R.drawable.romania;
            case "Greece" : return R.drawable.greece;
            case "Indonesia" : return R.drawable.indonesia;
            case "Iceland" : return R.drawable.iceland;
            case "Singapore" : return R.drawable.singapore;
            case "South Africa" : return R.drawable.southafrica;
            case "Philippines" : return R.drawable.philippines;
            case "India" : return R.drawable.india;
            case "Qatar" : return R.drawable.qatar;
            case "Russia" : return R.drawable.russia;
            case "Slovenia" : return R.drawable.slovenia;
            case "Peru" : return R.drawable.peru;
            case "Bahrain" : return R.drawable.bahrain;
            case "Hong Kong" : return R.drawable.hongkong;
            case "Croatia" : return R.drawable.croatia;
            case "Estonia" : return R.drawable.estonia;
            case "Mexico" : return R.drawable.mexico;
            case "Egypt" : return R.drawable.egypt;
            case "Panama" : return R.drawable.panama;
            case "Iraq" : return R.drawable.iraq;
            case "Dominican Republic" : return R.drawable.dominicanrepublic;
            case "Colombia" : return R.drawable.colombia;
            case "Lebanon" : return R.drawable.lebanon;
            case "Serbia" : return R.drawable.serbia;
            case "Argentina" : return R.drawable.argentina;
            case "Algeria" : return R.drawable.algeria;
            case "Armenia" : return R.drawable.armenia;
            case "Taiwan" : return R.drawable.taiwan;
            case "Slovakia" : return R.drawable.slovakia;
            case "Lithuania" : return R.drawable.lithuania;
            case "Bulgaria" : return R.drawable.bulgaria;
            case "UAE" : return R.drawable.unitedarabemirates;
            case "Latvia" : return R.drawable.latvia;
            case "Kuwait" : return R.drawable.kuwait;
            case "San Marino" : return R.drawable.sanmarino;
            case "Hungary" : return R.drawable.hungary;
            case "Andorra" : return R.drawable.andorra;
            case "Uruguay" : return R.drawable.uruguay;
            case "Costa Rica" : return R.drawable.costarica;
            case "New Zealand" : return R.drawable.newzealand;
            case "Bosnia and Herzegovina" : return R.drawable.bosniaandherzegovina;
            case "Macedonia" : return R.drawable.macedonia;
            case "Morocco" : return R.drawable.morocco;
            case "Vietnam" : return R.drawable.vietnam;
            case "Jordan" : return R.drawable.jordan;
            case "Moldova" : return R.drawable.moldova;
            case "Albania" : return R.drawable.albania;
            case "Faeroe Islands" : return R.drawable.faroeislands;
            case "Cyprus" : return R.drawable.cyprus;
            case "Burkina Faso" : return R.drawable.burkinafaso;
            case "Tunisia" : return R.drawable.tunisia;
            case "Malta" : return R.drawable.malta;
            case "Brunei" : return R.drawable.brunei;
            case "Sri Lanka" : return R.drawable.srilanka;
            case "Ukraine" : return R.drawable.ukraine;
            case "Cambodia" : return R.drawable.cambodia;
            case "Azerbaijan" : return R.drawable.azerbaijan;
            case "Senegal" : return R.drawable.senegal;
            case "Oman" : return R.drawable.oman;
            case "Venezuela" : return R.drawable.venezuela;
            case "Belarus" : return R.drawable.belarus;
            case "Afghanistan" : return R.drawable.afghanistan;
            case "Georgia" : return R.drawable.georgia;
            case "Kazakhstan" : return R.drawable.kazakhstan;
            case "Cameroon" : return R.drawable.cameroon;
            case "Palestine" : return R.drawable.palestine;
            case "Martinique" : return R.drawable.martinique;
            case "Trinidad and Tobago" : return R.drawable.trinidadandtobago;
            case "Ghana" : return R.drawable.ghana;
            case "Liechtenstein" : return R.drawable.liechtenstein;
            case "Uzbekistan" : return R.drawable.uzbekistn;
            case "Cuba" : return R.drawable.cuba;
            case "DRC" : return R.drawable.congo;
            case "Mauritius" : return R.drawable.mauritius;
            case "Nigeria" : return R.drawable.nigeria;
            case "Kyrgyzstan" : return R.drawable.kyrgyzstan;
            case "Bangladesh" : return R.drawable.bangladesh;
            case "Puerto Rico" : return R.drawable.puertorico;
            case "Rwanda" : return R.drawable.rwanda;
            case "Guam" : return R.drawable.guam;
            case "Honduras" : return R.drawable.honduras;
            case "Montenegro" : return R.drawable.montenegro;
            case "Bolivia" : return R.drawable.bolivia;
            case "Paraguay" : return R.drawable.paraguay;
            case "Macao" : return R.drawable.macao;
            case "Ivory Coast" : return R.drawable.ivorycoast;
            case "Kenya" : return R.drawable.kenya;
            case "Monaco" : return R.drawable.monaco;
            case "Jamaica" : return R.drawable.jamaica;
            case "Guatemala" : return R.drawable.guatemala;
            case "Guyana" : return R.drawable.guyana;
            case "Togo" : return R.drawable.togo;
            case "Barbados" : return R.drawable.barbados;
            case "Madagascar" : return R.drawable.madagascar;
            case "U.S. Virgin Islands" : return R.drawable.virginislands;
            case "Gibraltar" : return R.drawable.gibraltar;
            case "Maldives" : return R.drawable.maldives;
            case "Aruba" : return R.drawable.aruba;
            case "Ethiopia" : return R.drawable.ethiopia;
            case "Tanzania" : return R.drawable.tanzania;
            case "Mongolia" : return R.drawable.mongolia;
            case "Uganda" : return R.drawable.uganda;
            case "Seychelles" : return R.drawable.seychelles;
            case "Gabon" : return R.drawable.gabon;
            case "Benin" : return R.drawable.benin;
            case "Bermuda" : return R.drawable.bermuda;
            case "Haiti" : return R.drawable.haiti;
            case "Namibia" : return R.drawable.namibia;
            case "Suriname" : return R.drawable.suriname;
            case "Cayman Islands" : return R.drawable.caymanislands;
            case "El Salvador" : return R.drawable.salvador;
            case "Bahamas" : return R.drawable.bahamas;
            case "Congo" : return R.drawable.congo;
            case "Fiji" : return R.drawable.fiji;
            case "Greenland" : return R.drawable.greenland;
            case "Guinea" : return R.drawable.guinea;
            case "Sudan" : return R.drawable.sudan;
            case "Angola" : return R.drawable.angola;
            case "Antigua and Barbuda" : return R.drawable.antiguaandbarbuda;
            case "CAR" : return R.drawable.centralafricanrepublic;
            case "Chad" : return R.drawable.chad;
            case "Curaçao" : return R.drawable.curacao;
            case "Gambia" : return R.drawable.gambia;
            default: {
                Log.d("_MK missing flag: ", country);return R.drawable.noflag;}

        }
    }
}
