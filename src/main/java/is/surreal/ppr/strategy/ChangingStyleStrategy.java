/*
 * Project Puerto Rico
 * Copyright (C) 2018  Thomas Pötzsch, Stephan Stroh
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package is.surreal.ppr.strategy;

public interface ChangingStyleStrategy {
    void applyStyle();
}

// Nutzung
  /*
//GetUserRole
ChangingStyle changeStyle = new ChangingStyle(new NormalUserStyle());
if(userRole = "admin"){
        changeStyle.changeStrategy(new AdminStyle()):
        } else if(userRole = "operationOwner"){
        changeStyle.changeStrategy(new OperationOwnerStyle()):
        }
changeStyle.applyStyle();
*/
