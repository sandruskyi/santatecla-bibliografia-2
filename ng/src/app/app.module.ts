import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { JsonpModule } from '@angular/http';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { MatIconRegistry } from '@angular/material/icon';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';

import {
    MatButtonModule,
    MatListModule,
    MatIconModule,
    MatCardModule,
    MatMenuModule,
    MatInputModule,
    MatButtonToggleModule,
    MatProgressSpinnerModule,
    MatSelectModule,
    MatSlideToggleModule,
    MatDialogModule,
    MatSnackBarModule,
    MatToolbarModule,
    MatTabsModule,
    MatSidenavModule,
    MatTooltipModule,
    MatRippleModule,
    MatRadioModule,
    MatGridListModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSliderModule,
    MatAutocompleteModule,
} from '@angular/material';

import {
    CovalentCommonModule,
    CovalentLayoutModule,
    CovalentMediaModule,
    CovalentExpansionPanelModule,
    CovalentStepsModule,
    CovalentLoadingModule,
    CovalentDialogsModule,
    CovalentSearchModule,
    CovalentPagingModule,
    CovalentNotificationsModule,
    CovalentMenuModule,
    CovalentDataTableModule,
    CovalentMessageModule,
} from '@covalent/core';

import { NgxChartsModule } from '@swimlane/ngx-charts';
import { DomSanitizer } from '@angular/platform-browser';
import { BookService } from './book.service';
import { LoginService } from './auth/login.service';
import { BookDetailComponent } from './book-detail.component';
import { BookListComponent } from './book-list.component';
import { BookFormComponent } from './book-form.component';
import { LoginComponent } from './login.component';
import { routing } from './app.routing';
import { ErrorInterceptor } from './auth/error.interceptor';
import { BasicAuthInterceptor } from './auth/auth.interceptor';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';

@NgModule({
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        FormsModule,
        RouterModule.forRoot([]),
        HttpClientModule,
        JsonpModule,
        /** Material Modules */
        MatButtonModule,
        MatListModule,
        MatIconModule,
        MatCardModule,
        MatMenuModule,
        MatInputModule,
        MatSelectModule,
        MatButtonToggleModule,
        MatSlideToggleModule,
        MatProgressSpinnerModule,
        MatDialogModule,
        MatSnackBarModule,
        MatToolbarModule,
        MatTabsModule,
        MatSidenavModule,
        MatTooltipModule,
        MatRippleModule,
        MatRadioModule,
        MatGridListModule,
        MatDatepickerModule,
        MatNativeDateModule,
        MatSliderModule,
        MatAutocompleteModule,
        MatInputModule,
        /** Covalent Modules */
        CovalentCommonModule,
        CovalentLayoutModule,
        CovalentMediaModule,
        CovalentExpansionPanelModule,
        CovalentStepsModule,
        CovalentDialogsModule,
        CovalentLoadingModule,
        CovalentSearchModule,
        CovalentPagingModule,
        CovalentNotificationsModule,
        CovalentMenuModule,
        CovalentDataTableModule,
        CovalentMessageModule,
        /** Additional **/
        NgxChartsModule,
        routing,
    ],
    declarations: [AppComponent, BookDetailComponent, BookListComponent, BookFormComponent, LoginComponent],
    bootstrap: [AppComponent],
    providers: [BookService, LoginService,
        { provide: HTTP_INTERCEPTORS, useClass: BasicAuthInterceptor, multi: true },
        { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
        { provide: LocationStrategy, useClass: HashLocationStrategy }
    ],
})
export class AppModule {
    constructor(private matIconRegistry: MatIconRegistry, private domSanitizer: DomSanitizer) {
        matIconRegistry.addSvgIconSet(domSanitizer.bypassSecurityTrustResourceUrl('/assets/symbol-defs.svg'));
    }
}
