package r2s.com.spring.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import r2s.com.spring.web.dto.AddressDTO;
import r2s.com.spring.web.dto.response.AddressListResponseDTO;
import r2s.com.spring.web.dto.response.UserResponseDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/address")
public class AddressController {
    @PostMapping
    public ResponseEntity insertAddress(@RequestBody AddressDTO addressDTO) {
        String name = addressDTO.getName();
        String phone = addressDTO.getPhone();
        String district = addressDTO.getDistrict();
        String province = addressDTO.getProvince();
        String street = addressDTO.getStreet();
        String type = addressDTO.isType() ? "Home" : "Company";
        String defaultAddress = addressDTO.isDefaultAddress() ? "This is Default Address" : "This is Not Default Address";
        StringBuilder stringBuilder = new StringBuilder();
        String response = stringBuilder.append("Name: ").append(name).append(", phone: ").append(phone)
                .append("\nAddress: ").append(street).append(", ").append(province).append(", ").append(district)
                .append("\nType: ").append(type).append(". ").append(defaultAddress).toString();
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAllAddress() {
        AddressDTO addressDTO2 = new AddressDTO();
        addressDTO2.setName("Hieu");
        addressDTO2.setPhone("0987654332");
        addressDTO2.setProvince("Binh Dinh");
        addressDTO2.setDistrict("Phu My");
        addressDTO2.setStreet("QL1A");
        addressDTO2.setType(true);
        addressDTO2.setDefaultAddress(true);
        addressDTO2.setUserId(1);

        AddressDTO addressDTO1 = new AddressDTO();
        addressDTO1.setName("Huu Hieu");
        addressDTO1.setPhone("0987654332");
        addressDTO1.setProvince("Binh Dinh");
        addressDTO1.setDistrict("Phu My");
        addressDTO1.setStreet("QL1A");
        addressDTO1.setType(true);
        addressDTO1.setDefaultAddress(true);
        addressDTO1.setUserId(2);


        List<AddressDTO> addressList = new ArrayList<>();
        addressList.add(addressDTO1);
        addressList.add(addressDTO2);
        AddressListResponseDTO response = new AddressListResponseDTO();
        response.setAddressList(addressList);

        return new ResponseEntity(response, HttpStatus.OK);
    }
}
