import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'findLanguageFromKey' })
export class FindLanguageFromKeyPipe implements PipeTransform {
    private languages: any = {
        en: { name: 'English' },
        de: { name: 'Deutsch' },
        el: { name: 'Ελληνικά' },
        hu: { name: 'Magyar' },
        it: { name: 'Italiano' },
        ja: { name: '日本語' },
        pl: { name: 'Polski' },
        ru: { name: 'Русский' },
        sk: { name: 'Slovenský' },
        es: { name: 'Español' },
        sv: { name: 'Svenska' },
        tr: { name: 'Türkçe' }
        // jhipster-needle-i18n-language-key-pipe - JHipster will add/remove languages in this object
    };
    transform(lang: string): string {
        return this.languages[lang].name;
    }
}
