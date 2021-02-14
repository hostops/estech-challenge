import { element, by, ElementFinder } from 'protractor';

export class SystemUnitComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-system-unit div table .btn-danger'));
    title = element.all(by.css('jhi-system-unit div h2#page-heading span')).first();

    async clickOnCreateButton() {
        await this.createButton.click();
    }

    async clickOnLastDeleteButton() {
        await this.deleteButtons.last().click();
    }

    async countDeleteButtons() {
        return this.deleteButtons.count();
    }

    async getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class SystemUnitUpdatePage {
    pageTitle = element(by.id('jhi-system-unit-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    nameInput = element(by.id('field_name'));
    descriptionInput = element(by.id('field_description'));
    usersSelect = element(by.id('field_users'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setNameInput(name) {
        await this.nameInput.sendKeys(name);
    }

    async getNameInput() {
        return this.nameInput.getAttribute('value');
    }

    async setDescriptionInput(description) {
        await this.descriptionInput.sendKeys(description);
    }

    async getDescriptionInput() {
        return this.descriptionInput.getAttribute('value');
    }

    async usersSelectLastOption() {
        await this.usersSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async usersSelectOption(option) {
        await this.usersSelect.sendKeys(option);
    }

    getUsersSelect(): ElementFinder {
        return this.usersSelect;
    }

    async getUsersSelectedOption() {
        return this.usersSelect.element(by.css('option:checked')).getText();
    }

    async save() {
        await this.saveButton.click();
    }

    async cancel() {
        await this.cancelButton.click();
    }

    getSaveButton(): ElementFinder {
        return this.saveButton;
    }
}

export class SystemUnitDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-systemUnit-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-systemUnit'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
