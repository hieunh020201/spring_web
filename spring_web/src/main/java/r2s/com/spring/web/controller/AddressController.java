package r2s.com.spring.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import r2s.com.spring.web.dto.request.CreateAddressRequestDTO;
import r2s.com.spring.web.dto.request.UpdateAddressRequestDTO;
import r2s.com.spring.web.dto.response.AddressResponseDTO;
import r2s.com.spring.web.dto.response.PagingAddressListResponseDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/address")
public class AddressController {
    @PostMapping
    public ResponseEntity insertAddress(@RequestBody CreateAddressRequestDTO createAddressRequestDTO) {
        AddressResponseDTO response = new AddressResponseDTO();
        response.setId(1);
        response.setName(createAddressRequestDTO.getName());
        response.setPhone(createAddressRequestDTO.getPhone());
        response.setDistrict(createAddressRequestDTO.getDistrict());
        response.setProvince(createAddressRequestDTO.getProvince());
        response.setStreet(createAddressRequestDTO.getStreet());
        response.setType(createAddressRequestDTO.isType());
        response.setDefaultAddress(createAddressRequestDTO.isDefaultAddress());
        response.setUserId(createAddressRequestDTO.getUserId());

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping(value = "/get-all")
    public ResponseEntity getAllAddress(@RequestParam(value = "page", required = false) Integer page,
                                        @RequestParam(value = "size", required = false) Integer size,
                                        @RequestParam(value = "sort", required = false) String type_sort) {
        AddressResponseDTO addressResponseDTO2 = new AddressResponseDTO();
        addressResponseDTO2.setId(1);
        addressResponseDTO2.setName("Hieu");
        addressResponseDTO2.setPhone("0987654332");
        addressResponseDTO2.setProvince("Binh Dinh");
        addressResponseDTO2.setDistrict("Phu My");
        addressResponseDTO2.setStreet("QL1A");
        addressResponseDTO2.setType(true);
        addressResponseDTO2.setDefaultAddress(true);
        addressResponseDTO2.setUserId(1);

        AddressResponseDTO addressResponseDTO1 = new AddressResponseDTO();
        addressResponseDTO1.setId(2);
        addressResponseDTO1.setName("Huu Hieu");
        addressResponseDTO1.setPhone("0987654332");
        addressResponseDTO1.setProvince("Binh Dinh");
        addressResponseDTO1.setDistrict("Phu My");
        addressResponseDTO1.setStreet("QL1A");
        addressResponseDTO1.setType(true);
        addressResponseDTO1.setDefaultAddress(true);
        addressResponseDTO1.setUserId(2);

        PagingAddressListResponseDTO response = new PagingAddressListResponseDTO();

        List<AddressResponseDTO> addressList = new ArrayList<>();
        addressList.add(addressResponseDTO1);
        addressList.add(addressResponseDTO2);

        response.setAddressList(addressList);
        response.setPage(page);
        response.setSize(size);
        response.setSort(type_sort);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/{address-id}")
    public ResponseEntity getDetailAddressByAddressId(@PathVariable(value = "address-id") int addressId) {
        AddressResponseDTO response = new AddressResponseDTO();
        response.setId(addressId);
        response.setName("Huu Hieu");
        response.setPhone("0987654332");
        response.setProvince("Binh Dinh");
        response.setDistrict("Phu My");
        response.setStreet("QL1A");
        response.setType(true);
        response.setDefaultAddress(true);
        response.setUserId(2);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PutMapping("/{address-id}")
    public ResponseEntity updateAddress(@PathVariable(value = "address-id") int addressId,
                                     @RequestBody UpdateAddressRequestDTO updateAddressRequestDTO) {
        AddressResponseDTO response = new AddressResponseDTO();
        response.setId(addressId);
        response.setName(updateAddressRequestDTO.getName());
        response.setPhone(updateAddressRequestDTO.getPhone());
        response.setProvince(updateAddressRequestDTO.getProvince());
        response.setDistrict(updateAddressRequestDTO.getDistrict());
        response.setStreet(updateAddressRequestDTO.getStreet());
        response.setType(updateAddressRequestDTO.isType());
        response.setDefaultAddress(updateAddressRequestDTO.isDefaultAddress());
        response.setUserId(2);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping("/{address-id}")
    public ResponseEntity deleteAddress(@PathVariable(value = "address-id") int addressId) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
