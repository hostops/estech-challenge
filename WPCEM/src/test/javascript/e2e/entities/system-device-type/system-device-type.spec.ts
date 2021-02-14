/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { SystemDeviceTypeComponentsPage, SystemDeviceTypeDeleteDialog, SystemDeviceTypeUpdatePage } from './system-device-type.page-object';

const expect = chai.expect;

describe('SystemDeviceType e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let systemDeviceTypeUpdatePage: SystemDeviceTypeUpdatePage;
    let systemDeviceTypeComponentsPage: SystemDeviceTypeComponentsPage;
    let systemDeviceTypeDeleteDialog: SystemDeviceTypeDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load SystemDeviceTypes', async () => {
        await navBarPage.goToEntity('system-device-type');
        systemDeviceTypeComponentsPage = new SystemDeviceTypeComponentsPage();
        await browser.wait(ec.visibilityOf(systemDeviceTypeComponentsPage.title), 5000);
        expect(await systemDeviceTypeComponentsPage.getTitle()).to.eq('wpcemApp.systemDeviceType.home.title');
    });

    it('should load create SystemDeviceType page', async () => {
        await systemDeviceTypeComponentsPage.clickOnCreateButton();
        systemDeviceTypeUpdatePage = new SystemDeviceTypeUpdatePage();
        expect(await systemDeviceTypeUpdatePage.getPageTitle()).to.eq('wpcemApp.systemDeviceType.home.createOrEditLabel');
        await systemDeviceTypeUpdatePage.cancel();
    });

    it('should create and save SystemDeviceTypes', async () => {
        const nbButtonsBeforeCreate = await systemDeviceTypeComponentsPage.countDeleteButtons();

        await systemDeviceTypeComponentsPage.clickOnCreateButton();
        await promise.all([
            systemDeviceTypeUpdatePage.setNameInput('name'),
            systemDeviceTypeUpdatePage.setDescriptionInput('description'),
            systemDeviceTypeUpdatePage.deviceTypeSelectLastOption(),
            systemDeviceTypeUpdatePage.setDataSheetInput('dataSheet')
        ]);
        expect(await systemDeviceTypeUpdatePage.getNameInput()).to.eq('name');
        expect(await systemDeviceTypeUpdatePage.getDescriptionInput()).to.eq('description');
        expect(await systemDeviceTypeUpdatePage.getDataSheetInput()).to.eq('dataSheet');
        await systemDeviceTypeUpdatePage.save();
        expect(await systemDeviceTypeUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await systemDeviceTypeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last SystemDeviceType', async () => {
        const nbButtonsBeforeDelete = await systemDeviceTypeComponentsPage.countDeleteButtons();
        await systemDeviceTypeComponentsPage.clickOnLastDeleteButton();

        systemDeviceTypeDeleteDialog = new SystemDeviceTypeDeleteDialog();
        expect(await systemDeviceTypeDeleteDialog.getDialogTitle()).to.eq('wpcemApp.systemDeviceType.delete.question');
        await systemDeviceTypeDeleteDialog.clickOnConfirmButton();

        expect(await systemDeviceTypeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
